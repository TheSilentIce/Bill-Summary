import "../Styles/NavBar.css";
import owl from "../assets/owl.png";
import owl1 from "../assets/Owl_of_Minerva.png";

function NavBar() {
  const home_color = {
    color: "purple",
  };

  const spacing: React.CSSProperties = {
    paddingLeft: "3rem",
  };

  return (
    <>
      <div className="navbar" style={spacing}>
        {/* <img src={owl} className="owl-logo"></img> */}
        {/* <img src={owl1} className="owl-logo1"></img> */}
        <a href="../index.html" className="text-font" style={home_color}>
          Home
        </a>
        <a href="/Pages/summaries.html" className="text-font">
          Summaries
        </a>
      </div>
    </>
  );
}

export default NavBar;
