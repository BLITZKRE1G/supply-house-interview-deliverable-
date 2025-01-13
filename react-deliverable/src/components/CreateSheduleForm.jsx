import React, { useState } from "react";
import axios from "axios";
import "../styles.css";

const CreateScheduleForm = () => {
  const [formData, setFormData] = useState({
    date: "",
    // day: "MONDAY",
    startHour: 8,
    startMin: 0,
    startMeridiam: "AM",
    endHour: 5,
    endMin: 0,
    endMeridiam: "PM",
    phoneNumber: "",
    // refNo: "",
    specialDay: true
  });

  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const startDateTime = `${formData.date}T0${formData.startHour}:${formData.startMin < 10 ? "0" + formData.startMin : formData.startMin}:00`;
    let endDateTime;
    if (formData.endHour < 10 ) {
      if (formData.endMeridiam === 'PM')
        endDateTime=`${formData.date}T0${formData.endHour}:${formData.endMin < 10 ? "0" + formData.endMin : formData.endMin}:00`;
      else
        endDateTime=`${formData.date}T0${formData.endHour + 12}:${formData.endMin < 10 ? "0" + formData.endMin : formData.endMin}:00`;
    } else {
      if (formData.endMeridiam === 'PM')
        endDateTime=`${formData.date}T${formData.endHour}:${formData.endMin < 10 ? "0" + formData.endMin : formData.endMin}:00`;
      else
        endDateTime=`${formData.date}T${formData.endHour + 12}:${formData.endMin < 10 ? "0" + formData.endMin : formData.endMin}:00`;
    }
    const scheduleData = {
      date: formData.date,
      'start-dti': startDateTime, 
      'end-dti': endDateTime,     
      phoneNumber: formData.phoneNumber
    };


    try {
      console.log('Schedule Data:',scheduleData);
      
      const response = await axios.post(
        "http://localhost:8080/support-schedule/create-schedule",
        scheduleData,
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status === 201) {
        console.log("Schedule created successfully!".toUpperCase());
        
        setSuccess("Schedule created successfully!");
        setError(""); 
      }
    } catch (err) {
      setError("Failed to create the schedule.");
      setSuccess(""); 
    }
  };

  return (
    <div className="create-schedule-form">
      <p><em>Create New Support Schedule</em></p>

      {error && <p className="error">{error}</p>}
      {success && <p className="success">{success}</p>}

      <form onSubmit={handleSubmit}>
        <label htmlFor="date">Date:</label>
        <input
          type="date"
          id="date"
          name="date"
          value={formData.date}
          onChange={handleInputChange}
          required
        />

        <div className="time-picker">
          <div>
            <label>Start Time:</label>
            <input
              type="number"
              name="startHour"
              min="1"
              max="12"
              value={formData.startHour}
              onChange={handleInputChange}
            />
            :
            <input
              type="number"
              name="startMin"
              min="0"
              max="59"
              value={formData.startMin}
              onChange={handleInputChange}
            />
            <select
              name="startMeridiam"
              value={formData.startMeridiam}
              onChange={handleInputChange}
            >
              <option value="AM">AM</option>
              <option value="PM">PM</option>
            </select>
          </div>

          <div>
            <label>End Time:</label>
            <input
              type="number"
              name="endHour"
              min="1"
              max="12"
              value={formData.endHour}
              onChange={handleInputChange}
            />
            :
            <input
              type="number"
              name="endMin"
              min="0"
              max="59"
              value={formData.endMin}
              onChange={handleInputChange}
            />
            <select
              name="endMeridiam"
              value={formData.endMeridiam}
              onChange={handleInputChange}
            >
              <option value="AM">AM</option>
              <option value="PM">PM</option>
            </select>
          </div>
        </div>

        <label htmlFor="phoneNumber">Phone Number:</label>
        <input
          type="tel"
          id="phoneNumber"
          name="phoneNumber"
          value={formData.phoneNumber}
          onChange={handleInputChange}
          // defaultValue={888_551_7600}
          placeholder='8885517600'
        />
        <button type="submit">Create Schedule</button>
      </form>
    </div>
  );
};

export default CreateScheduleForm;
