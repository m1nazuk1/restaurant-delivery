import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Modal from './Modal';
import "./BookingPage.css"

const BookingPage = () => {
  const [tables, setTables] = useState([]);
  const [selectedTable, setSelectedTable] = useState<number | null>(null);
  const [startTime, setStartTime] = useState('');
  const [endTime, setEndTime] = useState('');
  const [bookingMessage, setBookingMessage] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false); // Для открытия/закрытия модала

  useEffect(() => {
    axios.get('http://localhost:8080/api/tables')
      .then((response) => setTables(response.data))
      .catch((error) => console.error(error));
  }, []);

  const handleBookTable = () => {
    if (!selectedTable || !startTime || !endTime) return;

    const visitorId = localStorage.getItem('visitorId');
    if (!visitorId) {
      alert('Пожалуйста, сначала укажите данные пользователя.');
      return;
    }

    axios.post('http://localhost:8080/api/bookings', {
      startTime,
      endTime,
      tableId: selectedTable,
      visitorId: parseInt(visitorId),
    })
      .then((response) => {
        setBookingMessage(`Столик успешно забронирован. ID брони: ${response.data.id}`);
        setSelectedTable(null);
        setStartTime('');
        setEndTime('');
      })
      .catch((error) => console.error(error));
  };

  return (
    <div className="booking-page">
      <h2>Забронировать столик</h2>
      <div className="booking-form">
        <label>Выберите время начала:</label>
        <input
          type="datetime-local"
          value={startTime}
          onChange={(e) => setStartTime(e.target.value)}
        />
        <label>Выберите время окончания:</label>
        <input
          type="datetime-local"
          value={endTime}
          onChange={(e) => setEndTime(e.target.value)}
        />
        <label>Выберите столик:</label>
        <button onClick={() => setIsModalOpen(true)}>Выбрать столик</button>
        {selectedTable ? (
          <p>Выбранный столик: {selectedTable}</p>
        ) : (
          <Modal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
            <h3>Список столов</h3>
            {tables.map((table: any) => (
              <div
                key={table.id}
                onClick={() => {
                  setSelectedTable(table.id);
                  setIsModalOpen(false); // Закрыть модал после выбора
                }}
                className="table-card"
              >
                <p>Стол №{table.number}</p>
                <p>{table.description}</p>
              </div>
            ))}
          </Modal>
        )}
        <button className="book-table-btn" onClick={handleBookTable}>
          Забронировать
        </button>
      </div>
      {bookingMessage && <p>{bookingMessage}</p>}
    </div>
  );
};

export default BookingPage;