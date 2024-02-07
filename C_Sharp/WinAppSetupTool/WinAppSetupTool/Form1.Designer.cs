namespace WinAppSetupTool
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            dataGridView = new DataGridView();
            Column0 = new DataGridViewTextBoxColumn();
            Column1 = new DataGridViewCheckBoxColumn();
            Column2 = new DataGridViewComboBoxColumn();
            Column3 = new DataGridViewTextBoxColumn();
            Column4 = new DataGridViewTextBoxColumn();
            comboBox = new ComboBox();
            menuStrip1 = new MenuStrip();
            stripMenuFile = new ToolStripMenuItem();
            openToolStripMenuItem = new ToolStripMenuItem();
            exitToolStripMenuItem = new ToolStripMenuItem();
            stripMenuEdit = new ToolStripMenuItem();
            toolStripMenuItem1 = new ToolStripMenuItem();
            btnInstall = new Button();
            tabControl1 = new TabControl();
            tabPage1 = new TabPage();
            tabPage2 = new TabPage();
            ((System.ComponentModel.ISupportInitialize)dataGridView).BeginInit();
            menuStrip1.SuspendLayout();
            tabControl1.SuspendLayout();
            tabPage1.SuspendLayout();
            SuspendLayout();
            // 
            // dataGridView
            // 
            dataGridView.AllowUserToAddRows = false;
            dataGridView.AllowUserToDeleteRows = false;
            dataGridView.Anchor = AnchorStyles.Top | AnchorStyles.Bottom | AnchorStyles.Left | AnchorStyles.Right;
            dataGridView.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridView.Columns.AddRange(new DataGridViewColumn[] { Column0, Column1, Column2, Column3, Column4 });
            dataGridView.EditMode = DataGridViewEditMode.EditOnEnter;
            dataGridView.Location = new Point(6, 35);
            dataGridView.Name = "dataGridView";
            dataGridView.Size = new Size(783, 355);
            dataGridView.TabIndex = 0;
            dataGridView.EditingControlShowing += dataGridView_EditingControlShowing;
            // 
            // Column0
            // 
            Column0.HeaderText = "Name";
            Column0.Name = "Column0";
            Column0.ReadOnly = true;
            Column0.Width = 340;
            // 
            // Column1
            // 
            Column1.HeaderText = "Check";
            Column1.Name = "Column1";
            Column1.Resizable = DataGridViewTriState.True;
            Column1.SortMode = DataGridViewColumnSortMode.Automatic;
            // 
            // Column2
            // 
            Column2.DisplayStyle = DataGridViewComboBoxDisplayStyle.ComboBox;
            Column2.HeaderText = "Version";
            Column2.Name = "Column2";
            Column2.Resizable = DataGridViewTriState.True;
            // 
            // Column3
            // 
            Column3.HeaderText = "Link";
            Column3.Name = "Column3";
            Column3.ReadOnly = true;
            // 
            // Column4
            // 
            Column4.AutoSizeMode = DataGridViewAutoSizeColumnMode.Fill;
            Column4.HeaderText = "Revese";
            Column4.Name = "Column4";
            Column4.ReadOnly = true;
            // 
            // comboBox
            // 
            comboBox.FormattingEnabled = true;
            comboBox.Location = new Point(6, 6);
            comboBox.Name = "comboBox";
            comboBox.Size = new Size(317, 23);
            comboBox.TabIndex = 1;
            // 
            // menuStrip1
            // 
            menuStrip1.Items.AddRange(new ToolStripItem[] { stripMenuFile, stripMenuEdit });
            menuStrip1.Location = new Point(0, 0);
            menuStrip1.Name = "menuStrip1";
            menuStrip1.Size = new Size(800, 24);
            menuStrip1.TabIndex = 2;
            menuStrip1.Text = "menuStrip1";
            // 
            // stripMenuFile
            // 
            stripMenuFile.DropDownItems.AddRange(new ToolStripItem[] { openToolStripMenuItem, exitToolStripMenuItem });
            stripMenuFile.Name = "stripMenuFile";
            stripMenuFile.Size = new Size(37, 20);
            stripMenuFile.Text = "File";
            // 
            // openToolStripMenuItem
            // 
            openToolStripMenuItem.Name = "openToolStripMenuItem";
            openToolStripMenuItem.Size = new Size(103, 22);
            openToolStripMenuItem.Text = "Open";
            // 
            // exitToolStripMenuItem
            // 
            exitToolStripMenuItem.Name = "exitToolStripMenuItem";
            exitToolStripMenuItem.Size = new Size(103, 22);
            exitToolStripMenuItem.Text = "Exit";
            // 
            // stripMenuEdit
            // 
            stripMenuEdit.DropDownItems.AddRange(new ToolStripItem[] { toolStripMenuItem1 });
            stripMenuEdit.Name = "stripMenuEdit";
            stripMenuEdit.Size = new Size(39, 20);
            stripMenuEdit.Text = "Edit";
            // 
            // toolStripMenuItem1
            // 
            toolStripMenuItem1.Name = "toolStripMenuItem1";
            toolStripMenuItem1.Size = new Size(150, 22);
            toolStripMenuItem1.Text = "Add New Data";
            // 
            // btnInstall
            // 
            btnInstall.Location = new Point(707, 6);
            btnInstall.Name = "btnInstall";
            btnInstall.Size = new Size(82, 23);
            btnInstall.TabIndex = 3;
            btnInstall.Text = "Download";
            btnInstall.UseVisualStyleBackColor = true;
            btnInstall.Click += btnInstall_Click;
            // 
            // tabControl1
            // 
            tabControl1.Controls.Add(tabPage1);
            tabControl1.Controls.Add(tabPage2);
            tabControl1.Dock = DockStyle.Fill;
            tabControl1.Location = new Point(0, 24);
            tabControl1.Name = "tabControl1";
            tabControl1.SelectedIndex = 0;
            tabControl1.Size = new Size(800, 426);
            tabControl1.TabIndex = 4;
            // 
            // tabPage1
            // 
            tabPage1.Controls.Add(dataGridView);
            tabPage1.Controls.Add(btnInstall);
            tabPage1.Controls.Add(comboBox);
            tabPage1.Location = new Point(4, 24);
            tabPage1.Name = "tabPage1";
            tabPage1.Padding = new Padding(3);
            tabPage1.Size = new Size(792, 398);
            tabPage1.TabIndex = 0;
            tabPage1.Text = "tabPage1";
            tabPage1.UseVisualStyleBackColor = true;
            // 
            // tabPage2
            // 
            tabPage2.Location = new Point(4, 24);
            tabPage2.Name = "tabPage2";
            tabPage2.Padding = new Padding(3);
            tabPage2.Size = new Size(792, 398);
            tabPage2.TabIndex = 1;
            tabPage2.Text = "tabPage2";
            tabPage2.UseVisualStyleBackColor = true;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(800, 450);
            Controls.Add(tabControl1);
            Controls.Add(menuStrip1);
            MainMenuStrip = menuStrip1;
            Name = "Form1";
            Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)dataGridView).EndInit();
            menuStrip1.ResumeLayout(false);
            menuStrip1.PerformLayout();
            tabControl1.ResumeLayout(false);
            tabPage1.ResumeLayout(false);
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private DataGridView dataGridView;
        private ComboBox comboBox;
        private MenuStrip menuStrip1;
        private ToolStripMenuItem stripMenuFile;
        private ToolStripMenuItem openToolStripMenuItem;
        private ToolStripMenuItem exitToolStripMenuItem;
        private ToolStripMenuItem stripMenuEdit;
        private ToolStripMenuItem toolStripMenuItem1;
        private Button btnInstall;
        private TabControl tabControl1;
        private TabPage tabPage1;
        private TabPage tabPage2;
        private DataGridViewTextBoxColumn Column0;
        private DataGridViewCheckBoxColumn Column1;
        private DataGridViewComboBoxColumn Column2;
        private DataGridViewTextBoxColumn Column3;
        private DataGridViewTextBoxColumn Column4;
    }
}
