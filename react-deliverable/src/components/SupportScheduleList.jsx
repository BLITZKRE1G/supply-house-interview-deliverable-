import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles.css";

const SupportScheduleList = () => {
  const [schedules, setSchedules] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/support-schedule/all-priority-schedules")
      .then((response) => {
        setSchedules(response.data);
        setLoading(false);
      })
      .catch((err) => {
        setError("Error fetching schedules", err);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <p>Loading schedules...</p>;
  }

  if (error) {
    return <p>{error}</p>;
  }

  return (
    <div>
      {/* <div className="schedule-list"> */}
      {schedules.map((schedule, index) => (
        <div className="contact-method">
          <p style={{ textAlign: 'center' }}>
            {console.log(schedule)}
            <b>{schedule["for-date"]}</b> <strong>{schedule["day"]}</strong><br />
            <span>{schedule["start-hour"]}{schedule["start-meridiam"]} - {schedule["end-hour"]}:{schedule["end-min"]}{schedule["end-meridiam"]}</span>
          </p>
        </div>
      ))}
      {/* </div> */}
    </div>
  );
};

export default SupportScheduleList;
