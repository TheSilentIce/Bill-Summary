import axios from "axios";

function Fetcher() {
  let url: string = "";
  async function get() {
    try {
      const response = await axios.get(
        "http://localhost:8080/v1/ai/getNames/1",
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      console.log("WORKS");
      console.log(response.data);
    } catch (error) {
      console.log(error);
    }
  }

  const handler = () => {
    get();
  };

  return (
    <>
      <div>
        <button onClick={handler}></button>
      </div>
    </>
  );
}

export default Fetcher;
