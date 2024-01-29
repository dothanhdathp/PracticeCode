/**
 * The preload script runs before. It has access to web APIs
 * as well as Electron's renderer process modules and some
 * polyfilled Node.js functions.
 *
 * https://www.electronjs.org/docs/latest/tutorial/sandbox
 */
function createButton (bname, btype) {
  // 1. Create the button
  var button = document.createElement('button');
  button.className = btype;
  button.innerHTML = bname;
  button.setAttribute("width", 200);
  // 2. Append somewhere
  var body = document.getElementsByTagName("body")[0];
  body.appendChild(button);

  // 3. Add event handler
  button.addEventListener ("click",
    function() {
      alert("did something");
    }
  );
}

function cpu() {
  var os = require('os');
  alert(os.cpus().valueOf());
}
/// Start windows application
window.addEventListener('DOMContentLoaded', () => {
  // const replaceText = (selector, text) => {
  //   const element = document.getElementById(selector)
  //   if (element) element.innerText = text
  // }

  // for (const type of ['chrome', 'node', 'electron']) {
  //   replaceText(`${type}-version`, process.versions[type])
  // }

  const button1 = document.getElementById("b1");
  button1.addEventListener("click", cpu);
})