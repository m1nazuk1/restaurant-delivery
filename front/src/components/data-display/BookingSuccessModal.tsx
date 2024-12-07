import React from 'react';

type BookingSuccessModalProps = {
  isOpen: boolean;
  onClose: () => void;
};

const BookingSuccessModal: React.FC<BookingSuccessModalProps> = ({ isOpen, onClose }) => {
  if (!isOpen) return null;

  return (
    <div className="modal">
      <div className="modal-content">
        <h2>Бронирование успешно!</h2>
        <button onClick={onClose}>Закрыть</button>
      </div>
    </div>
  );
};

export default BookingSuccessModal;