import React from 'react';
import './HomePage.css';

const HomePage: React.FC = () => {
  return (
    <div className="home-page">
      <div className="slider">
        <h2>Добро пожаловать в наш ресторан!</h2>
      </div>
      <div className="about-section">
        <h2>О нас</h2>
        <p>Мы предлагаем лучшие блюда и незабываемые впечатления. Добро пожаловать!</p>
      </div>
    </div>
  );
};

export default HomePage;