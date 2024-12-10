import React, { useState } from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar/Navbar';
import HomePage from './pages/HomePage/HomePage';
import BookingPage from './pages/BookingPage/BookingPage';
import DeliveryPage from './pages/DeliveryPage/DeliveryPage';
import CartPage from './pages/CartPage/CartPage';
import VisitorModal from './components/data-display/VisitorModal';
import { useVisitor, VisitorProvider } from './context/VisitorContext';

const App: React.FC = () => {
  const { visitorId, setVisitorId } = useVisitor();
  const [showVisitorModal, setShowVisitorModal] = useState<boolean>(!visitorId);

  const handleVisitorChange = (id: number) => {
    setVisitorId(id);
    setShowVisitorModal(false);
  };

  const handleResetVisitor = () => {
    setVisitorId(null);
    setShowVisitorModal(true);
  };

  return (
    <Router>
      {showVisitorModal ? (
        <VisitorModal onClose={() => setShowVisitorModal(false)} onVisitorSubmit={handleVisitorChange} />
      ) : (
        <div>
          <Navbar visitorId={visitorId} onVisitorChange={handleResetVisitor} />
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/booking" element={<BookingPage />} />
            <Route path="/delivery" element={<DeliveryPage />} />
            <Route path="/cart" element={<CartPage />} />
          </Routes>
        </div>
      )}
    </Router>
  );
};

const WrappedApp: React.FC = () => (
  <VisitorProvider>
    <App />
  </VisitorProvider>
);

export default WrappedApp;