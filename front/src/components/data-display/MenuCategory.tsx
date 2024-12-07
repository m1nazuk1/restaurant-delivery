import React, { useState, useEffect } from 'react';
import axios from 'axios';
import MenuItemCard from './MenuItemCard';

const MenuCategory = () => {
  const [categories, setCategories] = useState<any[]>([]);
  const [menuItems, setMenuItems] = useState<any[]>([]);

  // Запрос на получение всех категорий
  useEffect(() => {
    axios.get('http://localhost:8080/api/menu-categories')
      .then((response) => {
        setCategories(response.data);
      })
      .catch((error) => {
        console.error('Ошибка при получении категорий:', error);
      });
  }, []);

  // Запрос на получение всех блюд
  useEffect(() => {
    axios.get('http://localhost:8080/api/menu-items')
      .then((response) => {
        setMenuItems(response.data);
      })
      .catch((error) => {
        console.error('Ошибка при получении блюд:', error);
      });
  }, []);

  return (
    <div className="menu-category">
      {categories.length > 0 ? (
        categories.map((category) => (
          <div key={category.id} className="category-section">
            <h3>{category.name}</h3>
            <p>{category.description}</p>
            <div className="menu-items">
              {/* Передаем блюда, относящиеся к текущей категории по названию */}
              <MenuItemCard 
                categoryName={category.name} 
                menuItems={menuItems} 
              />
            </div>
          </div>
        ))
      ) : (
        <p>Категории меню не найдены.</p>
      )}
    </div>
  );
};

export default MenuCategory;