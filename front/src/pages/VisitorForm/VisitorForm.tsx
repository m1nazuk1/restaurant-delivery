import React, { useState } from 'react';
import axios from 'axios';
import "./VisitorForm.css"

interface VisitorFormProps {
  onVisitorSubmit: (id: number) => void;
}

const VisitorForm: React.FC<VisitorFormProps> = ({ onVisitorSubmit }) => {
  const [name, setName] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      const response = await axios.post('http://localhost:8080/api/visitors', { name, phoneNumber });
      onVisitorSubmit(response.data.id);
    } catch (error) {
      console.error('Ошибка при создании пользователя:', error);
    }
  };


  return (
    <div className="visitor-form-wrapper">
      <div className="visitor-form">
        <h1 className="visitor-form__title">Введите ваши данные</h1>
        <form onSubmit={handleSubmit} className="visitor-form__form">
          <input
            type="text"
            placeholder="Имя"
            value={name}
            onChange={(e) => setName(e.target.value)}
            className="visitor-form__input"
          />
          <input
            type="text"
            placeholder="Номер телефона"
            value={phoneNumber}
            onChange={(e) => setPhoneNumber(e.target.value)}
            className="visitor-form__input"
          />
          <button type="submit" className="visitor-form__button">Продолжить</button>
        </form>
      </div>
    </div>
  );
};

export default VisitorForm;