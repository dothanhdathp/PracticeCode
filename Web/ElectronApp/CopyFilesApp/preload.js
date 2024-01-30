/**
 * The preload script runs before. It has access to web APIs
 * as well as Electron's renderer process modules and some
 * polyfilled Node.js functions.
 *
 * https://www.electronjs.org/docs/latest/tutorial/sandbox
 */
var selected;
var disp;
function importFile() {
  let input = document.createElement('input');
  input.type = 'file';
  input.onchange = _ => {
    // you can use this method to get file and perform respective operations
    const files = Array.from(input.files);
    console.log(files);
    console.log(files[0].path);
    const ip = document.getElementById("source_dir");
    ip.value = files[0].path
  };
  input.click();
}

async function importFolder() {
  let input = document.createElement('input');
  input.type = 'file';
  input.innerText += "webkitdirectory";
  input.innerText += "directory";
  input.onchange = _ => {
    // you can use this method to get file and perform respective operations
    const files = Array.from(input.files);
    console.log(files);
    console.log(files[0].path);
    // const ip = document.getElementById("source_dir");
    // ip.value = files[0].path
  };
  input.click();
}

function alertSomething() {
  alert("did something");
}

function show(a, b)
{
  console.log(a + "; " + b);
  // document.getElementById(selected).style.backgroundColor = "rgb(150,150,150)";
  document.getElementById(disp).style.display = "none";
  // document.getElementById(a).style.backgroundColor = "rgb(200,200,200)";
  document.getElementById(b).style.display = "block";
  selected=a;
  disp=b;
}

function dosomething()
{
  console.log(a + "; " + b);
  // document.getElementById(selected).style.backgroundColor = "rgb(150,150,150)";
  document.getElementById(target_dir).style.display = "none";
  // document.getElementById(a).style.backgroundColor = "rgb(200,200,200)";
  document.getElementById(b).style.display = "block";
  selected=a;
  disp=b;
}

/// Start windows application
window.addEventListener('DOMContentLoaded', () => {

  selected="sel1";
  disp="resultsel1";

  const button1 = document.getElementById("source_file");
  button1.addEventListener("click", function() {
    let input = document.createElement('input');
    input.type = 'file';
    input.onchange = _ => {
      // you can use this method to get file and perform respective operations
      const files = Array.from(input.files);
      console.log(files);
      console.log(files[0].path);
      button1.innerHTML = files[0].path;
      button1.style.setProperty("background-color", "#aeffb9");
      button1.style.setProperty("text-align", "left");
    };
    input.click();
  });

  const button2 = document.getElementById("target_dir");
  button2.addEventListener("click", function() {
    let input = document.createElement('input');
    input.type = 'file';
    input.setAttribute("webkitdirectory","");
    input.setAttribute("directory","");
    input.onchange = _ => {
      // you can use this method to get file and perform respective operations
      const files = Array.from(input.files);
      console.log(files);
      console.log(files[0].path);
      filePath = files[0].path;
      dirPath = filePath.substring(0,filePath.lastIndexOf("\\")+1);
      button2.innerText = dirPath;
      button2.style.setProperty("background-color", "#aeffb9");
      button2.style.setProperty("text-align", "left");
    };
    input.click();
  });

  // const b2 = document.getElementById("btn_copy_file");
  // b2.addEventListener("click", importFolder);

  // const button2 = document.getElementById("b2");
  // button2.addEventListener("click", alertSomething);

  // const button3 = document.getElementById("b3");
  // button3.addEventListener("click", alertSomething);

  const item1 = document.getElementById("sel1");
  item1.addEventListener("click", function(){show("sel1", "resultsel1")});

  const item2 = document.getElementById("sel2");
  item2.addEventListener("click", function(){show("sel2", "resultsel2")});

  const item3 = document.getElementById("sel3");
  item3.addEventListener("click", function(){show("sel3", "resultsel3")});
})