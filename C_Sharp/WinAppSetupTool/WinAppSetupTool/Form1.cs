using Newtonsoft.Json;
//using System.Data;
//using System.Reflection;
using System.Net;
using static System.Windows.Forms.LinkLabel;

namespace WinAppSetupTool
{
    public partial class Form1 : Form
    {
        private ComboBox mSelectedComboBox;
        private DataGridViewCell mDataGridViewCurrentCell;
        private int COLUMN_INDEX_SOFTWARE = 0;
        private int COLUMN_INDEX_CHECKBOX = 1;
        private int COLUMN_INDEX_VERSION = 2;
        private int COLUMN_INDEX_DESCRIPTION = 3;
        private int COLUMN_INDEX_LINK = 4;
        private string mDefaultSaveDir = @"%USERPROFILE%\Downloads\";

        // Starting form
        public Form1()
        {
            InitializeComponent();
            AddRow();
        }

        private class GridViewData
        {
            public DataGridViewTextBoxCell textBoxCell = new DataGridViewTextBoxCell();
            // public DataGridViewCheckBoxCell checkBoxCell = new DataGridViewCheckBoxCell(); // Remove by always false
            public DataGridViewComboBoxCell comboBoxCell = new DataGridViewComboBoxCell();
            public DataGridViewTextBoxCell textBoxCellLink = new DataGridViewTextBoxCell();
            public DataGridViewTextBoxCell textBoxCellTemp = new DataGridViewTextBoxCell();
        }

        private class JItem
        {
            public string name, version, description, link;
        }

        private int DownloadFile(String remoteFilename, String localFilename)
        {
            // Function will return the number of bytes processed
            // to the caller. Initialize to 0 here.
            int bytesProcessed = 0;

            // Assign values to these objects here so that they can
            // be referenced in the finally block
            Stream remoteStream = null;
            Stream localStream = null;
            WebResponse response = null;

            // Use a try/catch/finally block as both the WebRequest and Stream
            // classes throw exceptions upon error
            try
            {
                // Create a request for the specified remote file name
                WebRequest request = WebRequest.Create(remoteFilename);
                if (request != null)
                {
                    // Send the request to the server and retrieve the
                    // WebResponse object 
                    response = request.GetResponse();
                    if (response != null)
                    {
                        // Once the WebResponse object has been retrieved,
                        // get the stream object associated with the response's data
                        remoteStream = response.GetResponseStream();

                        // Create the local file
                        localStream = File.Create(localFilename);

                        // Allocate a 1k buffer
                        byte[] buffer = new byte[1024];
                        int bytesRead;

                        // Simple do/while loop to read from stream until
                        // no bytes are returned
                        do
                        {
                            // Read data (up to 1k) from the stream
                            bytesRead = remoteStream.Read(buffer, 0, buffer.Length);

                            // Write the data to the local file
                            localStream.Write(buffer, 0, bytesRead);

                            // Increment total bytes processed
                            bytesProcessed += bytesRead;
                        } while (bytesRead > 0);
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
            finally
            {
                // Close the response and streams objects here
                // to make sure they're closed even if an exception
                // is thrown at some point
                if (response != null) response.Close();
                if (remoteStream != null) remoteStream.Close();
                if (localStream != null) localStream.Close();
            }

            // Return total bytes processed to caller.
            return bytesProcessed;
        }

        private Dictionary<string, List<JItem>> mDictionary = new Dictionary<string, List<JItem>>();
        public bool readJson()
        {
            bool fe = File.Exists("swdv.json");
            if (fe)
            {
                using (StreamReader r = new StreamReader(@".\swdv.json"))
                {
                    string json = r.ReadToEnd();
                    List<JItem> data = JsonConvert.DeserializeObject<List<JItem>>(json);

                    foreach (JItem j in data)
                    {
                        if (!mDictionary.ContainsKey(j.name))
                        {
                            mDictionary.Add(j.name, new List<JItem>());
                        }
                        mDictionary[j.name].Add(j);
                    }
                }
            }
            else
            {
                MessageBox.Show("Data file swdv.json not exist!");
            }
            return fe;
        }

        private void AddRow()
        {
            if (readJson())
            {
                foreach (var item in mDictionary)
                {
                    DataGridViewRow row = new DataGridViewRow();
                    DataGridViewTextBoxCell textBoxCell = new DataGridViewTextBoxCell();
                    DataGridViewCheckBoxCell checkBoxCell = new DataGridViewCheckBoxCell();
                    DataGridViewComboBoxCell comboBoxCell = new DataGridViewComboBoxCell();
                    DataGridViewTextBoxCell textBoxCellLink = new DataGridViewTextBoxCell();
                    DataGridViewTextBoxCell textBoxCellTemp = new DataGridViewTextBoxCell();

                    textBoxCell.Value = item.Key.ToString();
                    List<string> list = new List<string>();
                    checkBoxCell.Value = false;
                    foreach (JItem j in item.Value)
                    {
                        list.Add(j.version);
                    }
                    list.Sort();
                    list.Reverse();
                    foreach (string i in list)
                    {
                        comboBoxCell.Items.Add(i);
                    }
                    row.Cells.Add(textBoxCell);
                    row.Cells.Add(checkBoxCell);
                    row.Cells.Add(comboBoxCell);
                    row.Cells.Add(textBoxCellLink);
                    row.Cells.Add(textBoxCellTemp);
                    this.dataGridView.Rows.Add(row);
                }
            }
        }

        private void dataGridView_EditingControlShowing(object sender, DataGridViewEditingControlShowingEventArgs e)
        {
            if (e.Control is ComboBox)
            {
                mSelectedComboBox = (ComboBox)e.Control;
                if (mSelectedComboBox != null)
                {
                    mSelectedComboBox.SelectedIndexChanged += new EventHandler(cbm_SelectedIndexChanged);
                }
                mDataGridViewCurrentCell = this.dataGridView.CurrentCell;
            }
        }
        private void Debug(String data)
        {
            System.Diagnostics.Debug.WriteLine(data);
        }

        void cbm_SelectedIndexChanged(object sender, EventArgs e)
        {
            System.Diagnostics.Debug.WriteLine("On ComboBox Change [" + mDataGridViewCurrentCell.ColumnIndex + "," + mDataGridViewCurrentCell.RowIndex + "]");
            if (mSelectedComboBox != null && mDataGridViewCurrentCell != null)
            {
                int rowIndex = mDataGridViewCurrentCell.RowIndex;

                // Get the name of sofware
                String software_name = (string)this.dataGridView[COLUMN_INDEX_SOFTWARE, rowIndex].Value;
                String version = mSelectedComboBox.Text;

                List<JItem> list = mDictionary[software_name];
                foreach (JItem item in list)
                {
                    if (item.version.ToString() == version)
                    {
                        this.dataGridView[COLUMN_INDEX_CHECKBOX, rowIndex].Value = true;
                        // Update description data
                        this.dataGridView[COLUMN_INDEX_DESCRIPTION, rowIndex].Value = item.description;
                        // Update link data
                        this.dataGridView[COLUMN_INDEX_LINK, rowIndex].Value = item.link;
                    }
                }
            }
        }

        private void btnInstall_Click(object sender, EventArgs e)
        {
            for(int rowIndex = 0; rowIndex < dataGridView.Rows.Count; rowIndex++)
            {
                if ((bool)this.dataGridView[COLUMN_INDEX_CHECKBOX, rowIndex].Value)
                {
                    String link = (string)this.dataGridView[COLUMN_INDEX_LINK, rowIndex].Value;
                    Debug("Dowload >> " + link);
                    //WebClient Client = new WebClient();
                    //Client.DownloadFile(link, saveDir);
                    string fileName = System.IO.Path.GetFileName(link);
                    
                    try
                    {
                        WebClient client = new WebClient();
                        client.DownloadFile(link, Environment.ExpandEnvironmentVariables(mDefaultSaveDir + fileName));
                        client.DownloadFileCompleted += (s, e) =>
                        {
                            Debug("Dowload Complete!");
                            // any other code to process the file
                        };
                    }
                    catch (Exception er)
                    {
                        MessageBox.Show(er.ToString());
                        continue;
                    }
                }
            }
        }
    }
}
