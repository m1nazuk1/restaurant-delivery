import React, { useState } from 'react';

type TableModalProps = {
  isOpen: boolean;
  tables: Array<{ id: number; name: string }>;
  onClose: () => void;
  onSelectTable: (tableId: number) => void;
};

const TableModal: React.FC<TableModalProps> = ({ isOpen, tables, onClose, onSelectTable }) => {
  if (!isOpen) return null;

  return (
    <div className="modal">
      <div className="modal-content">
        <h2>Выберите столик</h2>
        <ul>
          {tables.map((table) => (
            <li key={table.id}>
              <button onClick={() => onSelectTable(table.id)}>{table.name}</button>
            </li>
          ))}
        </ul>
        <button onClick={onClose}>Закрыть</button>
      </div>
    </div>
  );
};

export default TableModal;