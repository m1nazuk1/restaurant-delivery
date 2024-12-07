import React, { useContext } from 'react';
import { CartContext } from '/Users/nizamialekperov/Downloads/restaurantFront/src/context/CartContext';
import axios from 'axios';
import "./CartPage.css"

export type CartItem = {
    id: number;
    name: string;
    price: number;
    quantity: number;
  };

const CartPage = () => {
  const cartContext = useContext(CartContext);

  // Handle if CartContext is null
  if (!cartContext) {
    return <p>Корзина недоступна. Пожалуйста, попробуйте позже.</p>;
  }

  const { cartItems, removeFromCart, clearCart } = cartContext;

  const handlePlaceOrder = () => {
    const visitorId = localStorage.getItem('visitorId');
    if (!visitorId) {
      alert('Пожалуйста, сначала укажите данные пользователя.');
      return;
    }

    const orderItems = cartItems.map((item) => ({
      menuItemId: item.id,
      quantity: item.quantity,
    }));

    axios.post('http://localhost:8080/api/order-items', {
      visitorId: parseInt(visitorId),
      orderItems,
    })
      .then(() => {
        alert('Заказ успешно оформлен!');
        clearCart();
      })
      .catch((error) => console.error(error));
  };

  return (
    <div className="cart-page">
      <h2>Корзина</h2>
      {cartItems.length > 0 ? (
        <div className="cart-items">
          {cartItems.map((item: CartItem) => (
            <div key={item.id} className="cart-item">
              <h4>{item.name}</h4>
              <p>Цена: {item.price} ₽</p>
              <p>Количество: {item.quantity}</p>
              <button onClick={() => removeFromCart(item.id)}>Удалить</button>
            </div>
          ))}
          <button onClick={clearCart}>Очистить корзину</button>
          <button onClick={handlePlaceOrder}>Оформить заказ</button>
        </div>
      ) : (
        <p>Корзина пуста</p>
      )}
    </div>
  );
};

export default CartPage;