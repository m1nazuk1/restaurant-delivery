import React, { useContext } from 'react';
import { CartContext } from '/Users/nizamialekperov/Downloads/restaurantFront/src/context/CartContext';

const MenuItemCard = ({ categoryName, menuItems }: { categoryName: string, menuItems: any[] }) => {
  const context = useContext(CartContext);
  if (!context) {
    throw new Error('CartContext is undefined');
  }

  const { addToCart } = context;

  // Фильтруем блюда по названию категории
  const filteredMenuItems = menuItems.filter(item => item.categoryName === categoryName);

  const handleAddToCart = (itemId: number, name: string, price: number) => {
    addToCart(itemId, name, price);
  };

  return (
    <div className="menu-items">
      {filteredMenuItems.length > 0 ? (
        filteredMenuItems.map((item) => (
          <div className="menu-item-card" key={item.id}>
            <h4>{item.name}</h4>
            <p>{item.description}</p>
            <p>Цена: {item.price} ₽</p>
            <button onClick={() => handleAddToCart(item.id, item.name, item.price)}>
              Добавить в корзину
            </button>
          </div>
        ))
      ) : (
        <p>Нет доступных блюд в этой категории.</p>
      )}
    </div>
  );
};

export default MenuItemCard;