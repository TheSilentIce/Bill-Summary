import "../Styles/App.css";
import MainBody from "./MainBody";
import NavBar from "../GlobalComponents/NavBar";
import Filler from "./Filler";

function App() {
  return (
    <>
      <div className="container">
        <NavBar></NavBar>
        <MainBody></MainBody>
        <Filler></Filler>
        <div className="attribution">
          Image by Martin Falbisoner, CC BY-SA 3.0, via{"  "}
          <a
            href="https://creativecommons.org/licenses/by-sa/3.0"
            className="link"
          >
            Wikimedia Commons
          </a>
        </div>
      </div>
    </>
  );
}

export default App;
