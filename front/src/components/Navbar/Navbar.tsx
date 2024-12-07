// src/components/Navbar/Navbar.tsx
import React from 'react';
import { Link } from 'react-router-dom';
import './Navbar.css';

interface NavbarProps {
  visitorId: number | null;
  onVisitorChange: () => void;
}

const Navbar: React.FC<NavbarProps> = ({ visitorId, onVisitorChange }) => {
  return (
    <nav className="navbar">
      <div className="navbar-container">
        <Link to="/" className="logo">Restaurant</Link>
        <ul className="nav-links">
          <li><Link to="/">Главная</Link></li>
          <li><Link to="/booking">Бронирование</Link></li>
          <li><Link to="/delivery">Доставка</Link></li>
          <li><Link to="/cart">Корзина</Link></li>
          {visitorId && (
            <li>
              <button onClick={onVisitorChange}>Сменить пользователя</button>
            </li>
          )}
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;