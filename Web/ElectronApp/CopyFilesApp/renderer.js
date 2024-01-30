/**
 * This file is loaded via the <script> tag in the index.html file and will
 * be executed in the renderer process for that window. No Node.js APIs are
 * available in this process because `nodeIntegration` is turned off and
 * `contextIsolation` is turned on. Use the contextBridge API in `preload.js`
 * to expose Node.js functionality from the main process.
 */
const setButton = document.getElementById('target_dir');
// const titleInput = document.getElementById('title');

setButton.addEventListener('click', () => {
  console.log("clicked!");
  window.electronAPI.openDialog();
})

bridge.whenPing().then(data => {
    console.log("whenPing");
    if(data.canceled) {
        setButton.innerHTML = "Empty";
    } else {
        setButton.innerHTML = data.filePaths[0];
    }
});

bridge.whenTest().then(data => {
    console.log("whenTest");
    setButton.innerHTML = data;
});

bridge.whenUpdate().then(data => {});