import React, { createContext, useState, useContext, ReactNode } from 'react';

interface VisitorContextType {
  visitorId: number | null;
  setVisitorId: (id: number | null) => void;
}

const VisitorContext = createContext<VisitorContextType | undefined>(undefined);

export const useVisitor = () => {
  const context = useContext(VisitorContext);
  if (!context) {
    throw new Error('useVisitor must be used within a VisitorProvider');
  }
  return context;
};

interface VisitorProviderProps {
  children: ReactNode;
}

export const VisitorProvider: React.FC<VisitorProviderProps> = ({ children }) => {
  const [visitorId, setVisitorId] = useState<number | null>(localStorage.getItem('visitorId') ? parseInt(localStorage.getItem('visitorId')!) : null);

  const handleSetVisitorId = (id: number | null) => {
    if (id) {
      localStorage.setItem('visitorId', id.toString());
    } else {
      localStorage.removeItem('visitorId');
    }
    setVisitorId(id);
  };

  return (
    <VisitorContext.Provider value={{ visitorId, setVisitorId: handleSetVisitorId }}>
      {children}
    </VisitorContext.Provider>
  );
};