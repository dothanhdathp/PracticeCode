const { app, BrowserWindow } = require('electron');
let mainWindow;

function createWindow() {
	mainWindow = new BrowserWindow({
		width: 1024,
		height: 800,
		webPreferences: {
			nodeIntegration: true
		}
	});

	promise = mainWindow.loadURL('https://hris.humaxdigital.com');

	mainWindow.on('activate', () => {
		console.log("App on active");
	})

	mainWindow.webContents.on('did-finish-load', () => {
		console.log("did-finish-load");
		// mainWindow.webContents.executeJavaScript('console.log("Hello from Tad")');
		mainWindow.webContents.executeJavaScript("$0");
	});

	mainWindow.on('ready', function () {
		mainWindow = null;
	});
}

app.on('ready', function() {
	console.log("App on ready");
	createWindow();
});

app.on('window-all-closed', function () {
	// On macOS it is common for applications and their menu bar
	// to stay active until the user quits explicitly with Cmd + Q
	if (process.platform !== 'darwin') {
		app.quit();
	}
});

app.on('activate', function () {
	console.log("App on activate");
	// On macOS it's common to re-create a window in the app when the
	// dock icon is clicked and there are no other windows open.
	if (mainWindow === null) {
		createWindow();
	}
});