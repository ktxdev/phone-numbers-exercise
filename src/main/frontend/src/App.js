import { useEffect, useState } from "react";
import { getAllCustomers } from "./api/lib/customer";
import Customers from "./components/Customers";
import Filter from "./components/Filter";
import Navbar from "./components/Navbar";

const App = () => {

  const [customers, setCustomers] = useState([]);

  useEffect(() => {
    fetchCustomers()
  }, [])
  

  const fetchCustomers = async (params = undefined) => {
    console.log(params);
    const response = await getAllCustomers(params);
    setCustomers(response.data)
    console.log(response.data);
  } 

  return (
    <>
      <Navbar />
      <Filter onFilter={fetchCustomers}/>
      <Customers data={customers} />
    </>
  );
}

export default App;
