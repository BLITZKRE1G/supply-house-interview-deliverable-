import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles.css";

// TimeDisplay component that calculates and renders time
const TimeDisplay = ({ hour, min, meridiam }) => {
  const formatTime = (time) => {
    let [hours, minutes] = time.split(':');
    hours = parseInt(hours, 10);
    const ampm = hours >= 12 ? 'PM' : 'AM';
    hours = hours % 12 || 12; // Convert hour to 12-hour format
    minutes = minutes.padStart(2, '0'); // Ensure minutes are always 2 digits
    return `${hours}:${minutes} ${ampm}`;
  };

  const time = `${hour} ${meridiam}`;

  useEffect(()=>{
    axios.get("http://localhost:8080/support-schedule/default-schedule/MONDAY")
    .then((res)=>{
        console.log(res);
        console.log(time);
        
    })
  })

  return (
    <span>
      {formatTime(startHour)} - {formatTime(endHour)}
    </span>
  );
};

export default TimeDisplay;
