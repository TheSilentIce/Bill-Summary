import "../Styles/mainBody.css";
import "../Styles/tailwind.css";
import { Button } from "flowbite-react";

function MainBody() {
  const click = () => {
    window.location.href = "./Pages/summaries.html";
  };

  return (
    <>
      <div className="main">
        <div className="left-body">
          <p className="title-text">Don't want to read 1000 line Bills?</p>
          <p className="regular-text">Now introducing AI-Summarized Bills!</p>
          <Button
            gradientDuoTone={"purpleToBlue"}
            className="button"
            onClick={click}
          >
            Summaries
          </Button>
        </div>
      </div>
    </>
  );
}

export default MainBody;
