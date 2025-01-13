import React, { useState, useEffect } from "react";
import axios from "axios";
import "../styles.css";
import CreateScheduleForm from "./CreateSheduleForm";
import SupportScheduleList from "./SupportScheduleList";
import GetScheduleByRefOrDate from "./GetScheduleByRefOrDate"

const SupportCard = () => {
  const [schedule, setSchedule] = useState(null);
  const [error, setError] = useState(null);


  useEffect(() => {
    axios
      .get("http://localhost:8080/support-schedule/login")
      .then((response) => {
        console.log("Response Data:", response.data);
        setSchedule(response.data);
      }).catch((err) => {
        setError(err.message);
        console.error("Error fetching schedule:", err);
      });
  }, []);
  const delayedDays = ['MONDAY', 'SATURDAY', 'SUNDAY'];

  return (
    <div className="support-card" style={{ textAlign: 'center' }}>

      {schedule ? (
        <div>
          <div className="header">
            <img
              src="https://thumbs.dreamstime.com/b/call-center-operator-headset-vector-retro-icon-female-call-center-avatar-client-services-communication-customer-support-100731258.jpg?w=768"
              alt="Support Representative"
              className="profile-pic"
            />
            <h2>Questions?</h2>
            <p>
              {!delayedDays.includes(schedule.day) ? 'Our Customer Support will be opening late today. We apologize for any inconvenience.' : 'Our Customer Support Executives will reach back to you'}
            </p>
          </div>
          {error && <p>Error: {error}</p>}
          <div className="contact-info" style={{ backgroundColor: '#EAF3FC', textAlign: 'center' }}>
            <div className="contact-method">
              <i className="icon-phone"></i>
              <p style={{textAlign: 'center'}}>
                  <b style={{color:'grey'}}><strong>Call</strong></b> {schedule["phone-number"]}<br />
                  <span>Available {schedule["start-hour"]}am</span>
              </p>
            </div>
            <div className="contact-method">
              <i className="icon-text"></i>
              <p style={{textAlign: 'center'}}>
                <b style={{color:'grey'}}><strong>Text</strong></b> {schedule["phone-number"]} <br />
                <span>Available {schedule["start-hour"]}am</span>
              </p>
            </div>
            <div className="contact-method">
              <i className="icon-chat"></i>
              <p style={{textAlign: 'center'}}>
                <b style={{color:'grey'}}><strong>Live Chat</strong></b> <br />
                <span>Available {schedule["start-hour"]}am</span>
              </p>
            </div>
            <div className="contact-method">
              <i className="icon-email"></i>
              <p style={{textAlign: 'center'}}>
                <b><a href="mailto:abc@email.com" style={{textDecoration:'none', color:'#0071BC'}}>EMAIL</a></b> <br />
                <span>Response by Sun</span>
              </p>
            </div>
          </div>
          <div className="hours">
            <div className="contact-method">
              <i className="icon-phone"></i>
              <p style={{ textAlign: 'center', color: 'red' }}>
                {(schedule["special-day"]) ? <div><span><strong>Special Hours</strong></span><br />
                  <span>{schedule["start-hour"]}.{schedule["start-min"]} {schedule["start-meridiam"]}- {schedule["end-hour"]}.{schedule["end-min"]}{schedule["end-meridiam"]}</span></div> : <strong><h3>Upcoming Schedules</h3></strong>}
                {console.log(schedule["start-hour"], ':', schedule["start-min"], '-', schedule["end-hour"], ':', schedule["end-hour"])}

              </p>
            </div>
            <div className="contact-method">
              <i className="icon-text"></i>
              <p style={{ textAlign: 'center' }}>
                <b>MONDAY - THURSDAY</b><br />
                {console.log(schedule["start-hour"])}
                <span>{schedule["mon-thr-st-hr"]}{schedule["mon-thr-st-mer"]} - {schedule["mon-thr-end-hr"]}:{schedule["mon-thr-end-min"]}{schedule["mon-thr-end-mer"]}</span>
              </p>
            </div>
            <div className="contact-method">
              <i className="icon-chat"></i>
              <p style={{ textAlign: 'center' }}>
                <b>FRIDAY</b> <br />
                <span>{schedule["fri-st-hr"]}{schedule["fri-st-mer"]} - {schedule["fri-end-hr"]}:{schedule["fri-end-min"]}{schedule["fri-end-mer"]}</span>
              </p>
            </div>
            <div className="contact-method">
              <i className="icon-chat"></i>
              <p style={{ textAlign: 'center' }}>
                <b>SATURDAY</b> <br />
                <span>{schedule["sat-sun-st-hr"]}{schedule["sat-sun-st-mer"]} - {schedule["sat-sun-end-hr"]}:{schedule["sat-sun-end-min"]}{schedule["sat-sun-end-mer"]}</span>
              </p>
            </div>
            <div className="contact-method">
              <i className="icon-chat"></i>
              <p style={{ textAlign: 'center' }}>
                <b>SUNDAY</b> <br />
                <span>{schedule["sat-sun-st-hr"]}{schedule["sat-sun-st-mer"]} - {schedule["sat-sun-end-hr"]}:{schedule["sat-sun-end-min"]}{schedule["sat-sun-end-mer"]}</span>
              </p>
            </div>
            <SupportScheduleList/>
          </div>
          <div>
            <CreateScheduleForm />
            <GetScheduleByRefOrDate/>
          </div>
        </div>
      ) : (
        <p>Loading schedule data...</p>
      )}
    </div>
  );
};

export default SupportCard;
