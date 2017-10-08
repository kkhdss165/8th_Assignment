import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class SimpleCal extends JFrame implements ActionListener
{
	private JTextField txt;
	private JPanel panel;
	private String[] labels = {
			"7","8","9","+",
			"4","5","6","-",
			"1","2","3","*",
			"0",".","=","/"
	};	
	String N1 ="" , N2 ="";//두 숫자
	String SignN1 ="", SignN2 ="", Exp="";//연산자와 숫자의 부호를 저장할 변수
	
	public SimpleCal()
	{
		setTitle("A calculator");
		
		txt = new JTextField(20);
		txt.setHorizontalAlignment(JTextField.RIGHT);
		add(txt, BorderLayout.NORTH);
		panel = new JPanel();
		panel.setLayout(new GridLayout(4, 4));
		add(panel, BorderLayout.CENTER);
		for (int i = 0; i < labels.length; i++) {
			JButton btn = new JButton(labels[i]);
			btn.addActionListener(this);
			btn.setPreferredSize(new Dimension(30, 30));
			panel.add(btn);
		}
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);

	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String Command = e.getActionCommand();
		//System.out.println(SignN1 + " " + N1 +" " + SignN2 +" "+ N2 +" " + Exp + " " + Command);
		/////////////////////////////////////
		if (txt.getText().length() == 0 && (Command == "*" || Command == "/" || Command == "="))
			return;
		if (Command == "=")
		{
			if(N2.length()!=0)
			{
				EXP();
			}
			return;
		}
		else if (Command == "+" || Command == "-")
		{
			if(N2.length()!=0)
			{
				EXP();	Exp = Command;
			}
			else if (SignN1.length() == 0)
				SignN1 = Command;
			else if (Exp.length() == 0)
			{
				Exp = Command;
			}
			else if (SignN2.length() == 0)
			{
				SignN2 = Command;
			}
			txt.setText(txt.getText()+Command);
			return;
		}
		else if (Command == "*" || Command == "/")
		{
			if(N2.length()!=0)
			{
				EXP();	Exp = Command;
			}
			else if (SignN1.length() == 0)
				return;
			else if (Exp.length() == 0)
			{
				Exp = Command;
			}
			else if (SignN2.length() == 0)
			{
				return;
			}
			txt.setText(txt.getText()+Command);
			return;
		}
		else if (Command == ".")
		{
			if (Exp == "")
			{
				if (N1.length()==0)
				{
					N1 = "0.";
					txt.setText(txt.getText()+"0.");
					return;
				}
				for(int i = 0;i<N1.length();i++)
				{
					if(N1.charAt(i) == '.')
						return;
				}
				N1 += Command;
				txt.setText(txt.getText()+Command);
				return;
			}
			else
			{
				if (N2.length()==0)
				{
					N2 = "0.";
					txt.setText(txt.getText()+"0.");
				}
				for(int i = 0;i<N2.length();i++)
				{
					if(N2.charAt(i) == '.')
						return;
				}
				N2 += Command;
				txt.setText(txt.getText()+Command);
				return;
			}
		}
		else //숫자
		{
			//나누기 0인경우는 제외 하였다
			if(Exp == "/" && N2 == "" && Command == "0")
				return;
			if (Exp == "")
			{
				if (SignN1.length()==0)
					SignN1 = "+";					
				
					N1 += Command;
			}
			else
			{
				if (SignN2.length()==0)
					SignN2 = "+";
				
					N2 += Command;
			}
		}
		/////////////////////////////////////
		
		
		txt.setText(txt.getText()+Command);
	}
	String EXPResult()
	{
		String Rt;
		double dA = Double.parseDouble(N1);
		double dB = Double.parseDouble(N2);
		
		if (SignN1 =="-")
			dA = -1 * dA;
		if (SignN2 =="-")
			dB = -1 * dB;
		
		switch(Exp)
		{
		case"+": Rt = Double.toString(dA + dB);	break;
		case"-": Rt = Double.toString(dA - dB); break;
		case"*": Rt = Double.toString(dA * dB); break;
		case"/": Rt = Double.toString(dA / dB); break;
		default: return "ERROR";
		}
		if (Rt.charAt(Rt.length()-2)=='.'&&Rt.charAt(Rt.length()-1)=='0')
		{
			String newRt = "";
			for(int i = 0; i< Rt.length()-2 ; i++)
				newRt += Rt.charAt(i);
			return newRt;
		}
		
		return Rt;
	}
	void EXP()
	{
		String tp = EXPResult();
		if (tp.charAt(0) == '-')
		{
			N1 = tp.replace("-","");	SignN1 ="-";
		}
		else
		{
			N1 = tp; 	SignN1 = "+";
		}
		Exp = "";	N2 = ""; SignN2 ="";
		txt.setText(tp);
		
			
		return;
	}
}
public class Assignment_8th
{
	public static void main(String args[])
	{
		SimpleCal S = new SimpleCal();
	}
}