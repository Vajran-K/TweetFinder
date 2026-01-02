const scanBtn = document.getElementById("scanBtn");
const resultBox = document.getElementById("result");

const BACKEND_URL = "http://localhost:8080/scan";

scanBtn.addEventListener("click", async () => {
  resultBox.innerText = "Scanning RSS feed...";

  try {
    const response = await fetch(BACKEND_URL);
    const text = await response.text();

    resultBox.innerText = text;
  } catch (err) {
    resultBox.innerText =
      "Backend not reachable. Is the server running?";
  }
});
