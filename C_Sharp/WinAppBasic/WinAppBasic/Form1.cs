using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using static System.Net.Mime.MediaTypeNames;
using System.Xml.Linq;
using static System.Windows.Forms.VisualStyles.VisualStyleElement.Window;



namespace WinAppBasic
{
    public partial class HomeForm : Form
    {
        private const int FILE_MODE      = 0;
        private const int DIRECTORY_MODE = 1;
        private OpenFileDialog mDialog;
        private FolderBrowserDialog mFolderDialog;
        private int mMode = FILE_MODE;

        private void init()
        {
            // Init dialog controller
            mDialog = new OpenFileDialog();
            mDialog.InitialDirectory = @"%USERPROFILE%";
            mDialog.RestoreDirectory = true;

            mFolderDialog = new FolderBrowserDialog();
            copyFileToolStripMenuItem.Checked = true;
        }

        public HomeForm()
        {
            InitializeComponent();
            init();
        }

        private void onClickSelectFile(object sender, EventArgs e)
        {
            if(FILE_MODE == mMode)
            {
                mDialog.ShowDialog();
                if (mDialog.CheckFileExists)
                {
                    textBox1.Text = mDialog.FileName;
                }
            } else
            {
                mFolderDialog.ShowDialog();
                textBox1.Text = mFolderDialog.SelectedPath;
            }
            
        }

        private void onClickSelectFolder(object sender, EventArgs e)
        {
            mFolderDialog.ShowDialog();
            textBox2.Text = mFolderDialog.SelectedPath;
        }

        private void bCopy_Click(object sender, EventArgs e)
        {
            try
            {
                if (FILE_MODE == mMode)
                {
                    /// DO COPY FILE
                    string src = textBox1.Text;
                    string des = textBox2.Text;
                    string fname = Path.GetFileNameWithoutExtension(src);
                    string ext = Path.GetExtension(src);

                    int num = Convert.ToInt32(numericUpDown.Value);
                    try
                    {

                        for (int i = 1; i <= num; ++i)
                        {
                            string new_des = des + "\\" + fname + '-' + i.ToString() + ext;
                            File.Copy(src, new_des, true);
                        }
                        MessageBox.Show("Copy total " + num);
                    }
                    catch (IOException iox)
                    {
                        MessageBox.Show(iox.Message);
                    }
                }
                else
                {
                    /// DO COPY FOLDER
                    DirectoryInfo src = new DirectoryInfo(textBox1.Text);
                    int num = Convert.ToInt32(numericUpDown.Value);
                    try
                    {
                        for (int i = 1; i <= num; ++i)
                        {
                            string new_name = src.Name + "-" + i.ToString();
                            DirectoryInfo new_des = new DirectoryInfo(textBox2.Text + "\\" + new_name);
                            if (!new_des.Exists)
                            {
                                new_des.Create();
                            }
                            CopyFilesRecursively(src, new_des);
                        }
                        MessageBox.Show("Copy total " + num);
                    }
                    catch (IOException iox)
                    {
                        MessageBox.Show(iox.Message);
                    }
                }
            } catch (Exception err)
            {
                MessageBox.Show(err.ToString());
            }
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void copyError()
        {
            MessageBox.Show("Copy Error!");
        }
        private void onSwitchMode()
        {
            copyFileToolStripMenuItem.Checked   = (mMode == FILE_MODE);
            copyFolderToolStripMenuItem.Checked = (mMode == DIRECTORY_MODE);
            bSelectFile.Text = (mMode == FILE_MODE) ? "Select File" : "Select Folder";
        }

        private void CopyFilesRecursively(DirectoryInfo source, DirectoryInfo target)
        {
            foreach (DirectoryInfo dir in source.GetDirectories())
                CopyFilesRecursively(dir, target.CreateSubdirectory(dir.Name));
            foreach (FileInfo file in source.GetFiles())
                file.CopyTo(Path.Combine(target.FullName, file.Name));
        }

        private void copyFileToolStripMenuItem_Click(object sender, EventArgs e)
        {
            mMode = FILE_MODE;
            onSwitchMode();
        }

        private void copyFolderToolStripMenuItem_Click(object sender, EventArgs e)
        {
            mMode = DIRECTORY_MODE;
            onSwitchMode();
        }
    }
}
