// npm install node-file-dialog
// directory: Opens directory selection prompt
// save-file: Opens save file prompt
// open-file: Opens open file prompt
// open-files: Opens open file prompt where multiple files can be selected
const dialog = require('node-file-dialog')
const config={type:'open-file'}
dialog(config)
    .then(dir => console.log(dir))
    .catch(err => console.log(err))