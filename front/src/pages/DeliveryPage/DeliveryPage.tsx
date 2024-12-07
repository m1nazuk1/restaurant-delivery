import React, { useState, useEffect } from 'react';
import axios from 'axios';
import MenuCategory from '/Users/nizamialekperov/Downloads/restaurantFront/src/components/data-display/MenuCategory';
import "./DeliveryPage.css"

const DeliveryPage = () => {
  const [paymentMethod, setPaymentMethod] = useState('card');
  const [address, setAddress] = useState('');
  const [comment, setComment] = useState('');
  const [type, setType] = useState('courier');
  const [categories, setCategories] = useState([]);
  const [deliverySaved, setDeliverySaved] = useState(false);

  useEffect(() => {
    if (deliverySaved) {
      axios.get('http://localhost:8080/api/menu-categories')
        .then((response) => setCategories(response.data))
        .catch((error) => console.error(error));
    }
  }, [deliverySaved]);

  const handleSaveDelivery = () => {
    const visitorId = localStorage.getItem('visitorId');
    if (!visitorId) {
      alert('Пожалуйста, сначала укажите данные пользователя.');
      return;
    }

    axios.post('http://localhost:8080/api/deliveries', {
      paymentMethod,
      address,
      comment,
      type,
      visitorId: parseInt(visitorId),
      orderItems: [],
    })
      .then(() => setDeliverySaved(true))
      .catch((error) => console.error(error));
  };

  return (
    <div className="delivery-page">
      {!deliverySaved ? (
        <div className="delivery-form">
          <h2>Оформить доставку</h2>
          <label>Способ оплаты:</label>
          <select value={paymentMethod} onChange={(e) => setPaymentMethod(e.target.value)}>
            <option value="card">Карта</option>
            <option value="cash">Наличные</option>
          </select>
          <label>Адрес доставки:</label>
          <input
            type="text"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />
          <label>Комментарий:</label>
          <textarea
            value={comment}
            onChange={(e) => setComment(e.target.value)}
          ></textarea>
          <label>Тип доставки:</label>
          <select value={type} onChange={(e) => setType(e.target.value)}>
            <option value="courier">Курьер</option>
            <option value="pickup">Самовывоз</option>
          </select>
          <button onClick={handleSaveDelivery} className="save-delivery-btn">
            Сохранить
          </button>
        </div>
      ) : (
        <div className="menu-categories">
          <h2>Выберите блюда</h2>
          {categories.map((category: any) => (
            <MenuCategory />
          ))}
        </div>
      )}
    </div>
  );
};

export default DeliveryPage;