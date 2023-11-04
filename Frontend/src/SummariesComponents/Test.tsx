import React, { useState, useEffect } from "react";
import axios from "axios";

import "../Styles/tailwind.css";
import "../Styles/BillList.css";
import { Button } from "flowbite-react";

interface Bill {
  name: string;
  summary: string;
}

const BillList: React.FC = () => {
  const [bills, setBills] = useState<Bill[]>([]);
  const [currentPageBill, setCurrentPageBill] = useState<Bill | null>(null);
  const [index, setNumber] = useState<number | null>(null);
  const [isLoading, setIsLoading] = useState(false);

  const getNames = async (min: number) => {
    if (isLoading) return; // Prevent multiple requests

    setIsLoading(true);
    const URL = "http://localhost:8080/v1/ai/findResponse/";

    try {
      const newBills: Bill[] = [];

      for (let i = min; i < min + 31; i++) {
        console.log("INDEX: " + i);

        const response = await axios.get(URL + i, {
          headers: {
            "Content-Type": "application/json",
          },
        });

        newBills.push({ name: response.data[0], summary: response.data[1] });
      }

      setBills(newBills);
    } catch (error) {
      console.log(error);
    } finally {
      setIsLoading(false);
    }
  };

  const handleClick = () => {
    const input = prompt("Enter number: ");
    if (input !== null) {
      setNumber(Number(input));
      getNames(Number(input));
    }
  };

  const handleBillClick = (bill: Bill) => {
    setCurrentPageBill(bill);
  };

  useEffect(() => {
    if (index !== null) {
      getNames(index);
    }
  }, [index]);

  return (
    <div>
      <div>
        <Button
          disabled={isLoading} // Disable the button while loading
          onClick={handleClick}
        >
          Click Me!
        </Button>
        <p>{index}</p>
      </div>

      <h1>Bills</h1>
      {isLoading ? (
        <p>Loading...</p>
      ) : (
        <ul>
          {bills.slice(0, 10).map((bill, idx) => (
            <li key={`${bill.name}-${idx}`}>
              <a href="#" onClick={() => handleBillClick(bill)}>
                {bill.name}
              </a>
            </li>
          ))}
        </ul>
      )}
      {currentPageBill && (
        <div>
          <h2>{currentPageBill.name}</h2>
          <p>{currentPageBill.summary}</p>
        </div>
      )}
    </div>
  );
};

export default BillList;
