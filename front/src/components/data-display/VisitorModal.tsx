
import React, { useState } from 'react';
import axios from 'axios';
import { useVisitor } from '../../context/VisitorContext';
import "./VisitorModal.css"

interface VisitorModalProps {
  onClose: () => void;
  onVisitorSubmit: (id: number) => void;
}

const VisitorModal = ({ onVisitorSubmit }: VisitorModalProps) => {
  const { setVisitorId } = useVisitor();
  const [name, setName] = useState('');
  const [phoneNumber, setPhone] = useState('');

  const handleSubmit = () => {
    axios.post('http://localhost:8080/api/visitors', { name, phoneNumber })
      .then((response) => {
        setVisitorId(response.data.id); // Используем контекст для установки visitorId
        onVisitorSubmit(response.data.id);
        alert('Данные успешно сохранены.');
      })
      .catch((error) => console.error(error));
  };

  return (
    <div className="visitor-modal">
      <div className='close-btn'><button className="close-btn" onClick={() => onVisitorSubmit(-1)}></button></div>
      <h2>Введите данные</h2>
      <label>Имя:</label>
      <input type="text" value={name} onChange={(e) => setName(e.target.value)} />
      <label>Телефон:</label>
      <input type="tel" value={phoneNumber} onChange={(e) => setPhone(e.target.value)} />
      <button onClick={handleSubmit}>Сохранить</button>
    </div>
  );
};

export default VisitorModal;