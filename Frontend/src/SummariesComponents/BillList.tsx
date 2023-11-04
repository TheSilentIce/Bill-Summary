import React, { useState, useEffect } from "react";
import axios from "axios";
import "../Styles/tailwind.css";
import "../Styles/BillList.css";
import { Button } from "flowbite-react";
import NavBar from "../GlobalComponents/NavBar";

interface Bill {
  name: string;
  summary: string;
}

const BillList: React.FC = () => {
  const [bills, setBills] = useState<Bill[]>([]);
  const [currentPageBill, setCurrentPageBill] = useState<Bill | null>(null);
  const [page, setPage] = useState(1);
  const [isLoading, setIsLoading] = useState(false);

  const getNames = async (pageNumber: number) => {
    if (isLoading) return; // Prevent multiple requests

    setIsLoading(true);
    const URL = "http://localhost:8080/v1/ai/findResponse/";

    try {
      const newBills: Bill[] = [];

      for (let i = (pageNumber - 1) * 30 + 1; i <= pageNumber * 30; i++) {
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

  const handleClickUp = () => {
    if (page + 1 <= 28) {
      setPage(page + 1);
      getNames(page + 1);
    }
  };

  const handleClickDown = () => {
    if (page - 1 >= 1) {
      setPage(page - 1);
      getNames(page - 1);
    }
  };

  const handleBillClick = (bill: Bill) => {
    setCurrentPageBill(bill);
  };

  useEffect(() => {
    getNames(page);
  }, [page]);

  return (
    <div className="container">
      <div className="nav">
        <NavBar></NavBar>
      </div>

      <div className="button-nav">
        <div className="pages">
          <Button
            disabled={isLoading || page === 1}
            onClick={handleClickDown}
            gradientDuoTone={"greenToBlue"}
          >
            Prev
          </Button>
          <p className="page-number">{page}</p>
          <Button
            disabled={isLoading || page === 18}
            onClick={handleClickUp}
            gradientDuoTone={"greenToBlue"}
          >
            Next
          </Button>
        </div>
      </div>

      <div className="links">
        <h1>Bills</h1>
        {isLoading ? (
          <p>Loading...</p>
        ) : (
          <ul>
            {bills.map((bill, idx) => (
              <li key={`${bill.name}-${idx}`}>
                <a href="#" onClick={() => handleBillClick(bill)}>
                  {bill.name}
                </a>
              </li>
            ))}
          </ul>
        )}
      </div>

      <div className="summary-title">SUMMARIES</div>

      <div className="summary">
        {currentPageBill && (
          <div>
            <pre>
              <h2>{currentPageBill.name}</h2>
              <p>{currentPageBill.summary}</p>
            </pre>
          </div>
        )}
      </div>
    </div>
  );
};

export default BillList;
