import React, { useState, useEffect } from "react";
import { Drawer } from "antd";
import { AiOutlineArrowUp, AiOutlineArrowDown } from "react-icons/ai";
import "antd/dist/reset.css";
import axios from "axios";

function DataTable() {
  const [data, setData] = useState([]);
  const [isDrawerVisible, setDrawerVisible] = useState(false);
  const path = "http://localhost:8080/Distance/GetDistance";

  const fetchData = async () => {
    try {
      const response = await axios.get(`${path}`);
      const formattedData = response.data.map((item) => ({
        date: new Date(item.id).toLocaleString(),
        distance: parseFloat(item.distance).toFixed(2),
      }));
      setData(formattedData);
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  // Function to open the drawer
  const openDrawer = () => {
    setDrawerVisible(true);
  };

  // Function to close the drawer
  const closeDrawer = () => {
    setDrawerVisible(false);
  };

  // Function to handle new button click
  const handleNewButtonClick = async () => {
    try {
        const response = await axios.post("http://localhost:8080/Distance/sendSignal", {
            signal: "Button Pressed"
        });
        console.log("Signal sent to backend:", response.data);
    } catch (error) {
        console.error("Error sending signal:", error);
    }
};

  // Get the latest and second latest distances
  const latestDistance = parseFloat(data[0]?.distance || 0);
  const secondLatestDistance = parseFloat(data[1]?.distance || 0);

  // Determine the arrow direction based on the comparison
  const arrowDirection =
    latestDistance > secondLatestDistance ? (
      <AiOutlineArrowUp className="text-green-500 inline" />
    ) : (
      <AiOutlineArrowDown className="text-red-500 inline" />
    );

  return (
    <div className="w-screen min-h-screen bg-gray-900 flex justify-center items-center text-gray-300">
      <div className="bg-gray-800 shadow-lg rounded-lg w-1/3 relative p-4">
        {/* Left Section: Latest Data */}
        <h1 className="text-center text-4xl">Distance Measuring Device</h1>
        <div className="flex justify-around p-2 rounded-lg">
          <div className="flex-col justify-around">
            <div className="text-2xl m-1">DATE:</div>
            <div>{data[0]?.date}</div>
          </div>
          <div className="text-2xl m-1 flex flex-col">
            <div>DISTANCE:</div>
            <div>
              {data[0]?.distance} m {arrowDirection}
            </div>
          </div>
        </div>
        {/* Button to open the drawer, positioned to the right */}
        <button
          className="bg-blue-600 text-white w-full px-4 py-2 rounded-lg hover:bg-blue-700"
          onClick={openDrawer}
        >
          Old Measurements
        </button>
        {/* New Button to send signal or perform an action */}
        <button
          className="bg-green-600 text-white w-full px-4 py-2 rounded-lg mt-4 hover:bg-green-700"
          onClick={handleNewButtonClick}
        >
          Send Signal to ESP32
        </button>
        {/* Right Section: Other Items inside Drawer */}
        <Drawer
          title="Old Measurements"
          placement="right"
          onClose={closeDrawer}
          open={isDrawerVisible}
          width={400}
          headerStyle={{ backgroundColor: "#1F1F1F", color: "white" }}
          bodyStyle={{ backgroundColor: "#2D2D2D", color: "white" }}
          drawerStyle={{ backgroundColor: "#1F1F1F" }}
          closeIcon={<span style={{ color: "white" }}>X</span>}
        >
          <div className="space-y-4">
            {data.slice(1).map((item, index) => (
              <div key={index} className="bg-gray-950 p-3 rounded-lg">
                <p className="font-semibold text-gray-200">Date: {item.date}</p>
                <p>Distance (m): {item.distance}</p>
              </div>
            ))}
          </div>
        </Drawer>
      </div>
    </div>
  );
}

export default DataTable;