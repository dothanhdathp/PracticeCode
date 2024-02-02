using System.Drawing;

namespace WinAppSetupTool
{
    public partial class Form1 : Form
    {
        private void init()
        {
            AddRow("Visual Studio Code", new String[] { "A", "B", "C" });
            AddRow("Notepad++", new String[] { "A", "B", "C" });
            AddRow("QtCreator", new String[] { "A", "B", "C" });
            AddRow("Python", new String[] { "A", "B", "C" });
            AddRow("Electron", new String[] { "A", "B", "C" });
            AddRow("Electron Fiddle", new String[] { "A", "B", "C" });
            
            AddRow("Rainmeter", new String[] { "A", "B", "C" });
            AddRow("RocketDock", new String[] { "A", "B", "C" });
        }

        private void AddRow(String name, String[] version_array)
        {
            DataGridViewRow row = new DataGridViewRow();
            DataGridViewTextBoxCell  textBoxCell = new DataGridViewTextBoxCell();
            DataGridViewCheckBoxCell checkBoxCell = new DataGridViewCheckBoxCell();
            DataGridViewComboBoxCell comboBoxCell = new DataGridViewComboBoxCell();
            DataGridViewTextBoxCell  textBoxCellLink = new DataGridViewTextBoxCell();
            DataGridViewTextBoxCell  textBoxCellTemp = new DataGridViewTextBoxCell();

            textBoxCell.Value = name;
            checkBoxCell.Value = false;
            comboBoxCell.Items.AddRange(version_array);
            textBoxCellLink.Value = ""; // Will be update after selection
            textBoxCellTemp.Value = ""; // Will be update after selection

            row.Cells.Add(textBoxCell);
            row.Cells.Add(checkBoxCell);
            row.Cells.Add(comboBoxCell);
            row.Cells.Add(textBoxCellLink);
            row.Cells.Add(textBoxCellTemp);
            this.dataGridView.Rows.Add(row);
        }

        // Starting form
        public Form1()
        {
            InitializeComponent();
            init();
        }
    }
}
