import React, { useState } from "react";
import axios from "axios";
import "../styles.css";

const GetScheduleByRefOrDate = () => {
  const [referenceNumber, setReferenceNumber] = useState("");
  const [date, setDate] = useState("");
  const [schedule, setSchedule] = useState(null);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    if (name === "referenceNumber") {
      setReferenceNumber(value);
    } else if (name === "date") {
      setDate(value);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setSchedule(null);
    setError("");
    const apiUrl = `http://localhost:8080/support-schedule/${date}`;

    try {
      const response = await axios.get(apiUrl);

      if (response.status === 200) {
        setSchedule(response.data);
        setError("");
      } else {
        setError("No schedule found.");
      }
    } catch (err) {
        console.log(err);
      setError("Failed to fetch the schedule. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="get-schedule-form" style={{backgroundColor: '#EAF3FC'}}>
      <strong><p>Get Schedule by Reference Number or Date</p></strong>

      {error && <p className="error">{error}</p>}
      {loading && <p>Loading...</p>} {/* Show loading message */}

      <form onSubmit={handleSubmit}>
        <label htmlFor="referenceNumber"><em>Reference Number:</em></label>
        <input
          type="text"
          id="referenceNumber"
          name="referenceNumber"
          value={referenceNumber}
          onChange={handleInputChange}
          placeholder="Enter Reference Number"
        />

        <label htmlFor="date"><em>Date:</em></label>
        <input
          type="date"
          id="date"
          name="date"
          value={date}
          onChange={handleInputChange}
        />

        <button type="submit" disabled={loading}>
          {loading ? "Fetching..." : "Get Schedule"}
        </button>
      </form>

      {schedule && (
        <div className="schedule-details">
          <h3>Schedule Details</h3>
          <p><strong>Date:</strong> {schedule["for-date"]}</p>
          <p><strong>Start Time:</strong> {schedule["start-hour"]}:{schedule["start-min"]} {schedule["start-meridiam"]}</p>
          <p><strong>End Time:</strong> {schedule["end-hour"]}:{schedule["end-min"]} {schedule["end-meridiam"]}</p>
          <p><strong>Phone Number:</strong> {schedule["phone-number"]}</p>
          <p><strong>Reference Number:</strong> {schedule["REF-"+schedule]}</p>
        </div>
      )}
    </div>
  );
};

export default GetScheduleByRefOrDate;
