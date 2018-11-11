package myFile;

import java.io.Serializable;

import disk.MyDisk;

public class ExeFile extends MyFile implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	//--------------------------------������----------------------------
	/**
	 * �ı�����
	 */
	private String text;
	/**
	 * ָ��
	 */
	private String[] orders;

	//-------------------------------���췽��----------------------------
	/**
	 * ����ʱ���õĹ��췽��
	 * @param name�ļ���
	 */
	public ExeFile(String name, String text) {
		super(name);
		this.text = text;
		//��չ��Ϊe
		this.extensionName = 'e';
		this.attribute = 'o';
	}

	/**
	 * �½�ʱ���õĹ��췽��
	 * @param parent��Ŀ¼
	 */
	public ExeFile(MyFile parent){
		super(parent);
		this.name = "EXE";
		this.extensionName = 'e';
		this.attribute = 'o';
	}

	/**
	 * ʵ�ִ����Ŀ�ִ���ļ����캯��
	 * @param name
	 * @param orders
	 */
	public ExeFile(String name, String[] orders, MyFile parent){
		super(name);
		this.orders = orders;
		this.deletable = false;
		this.extensionName = 'e';
		this.attribute = 'o';
		this.size += orders.length;
		String orderText = "";
		for (int i = 0; i < orders.length; i++) {
			orderText = orderText + orders[i] + '\n';
		}
		this.text = orderText;
	}

	//----------------------------------�����෽��-----------------------------
	/**
	 * �ļ�����
	 * ����ExeFile����δָ����Ŀ¼
	 */
	@Override
	public MyFile copy() {
		ExeFile file = new ExeFile(this.name, this.text);
		file.setText(this.text);
		file.setSize(this.size);
		return file;
	}

	//-----------------------------------set����------------------------------
	/**
	 * �޸��ı�����
	 * @param text
	 */
	public void setText(String text){
		this.text = text;
		int oldSize = this.size;
		//�ļ���С=������+���Դ�С
		orders = text.split("\\n+");
		this.size = orders.length + 8;
		//�ļ���С�ĸı�
		int changeSize  = this.size - oldSize;
		//ѭ���ı丸Ŀ¼�Ĵ�С
		MyFile parent = this.parent;
		while (parent != null) {
			parent.setSize(parent.getSize() + changeSize);
			parent = parent.getParent();
		}
		MyDisk.getDisk().getFat().changeByTextSize(this.size, this.originNum);
	}


	//----------------------------------get����-------------------------------
	/**
	 *������������
	 * @return
	 */
	public String[] getOrders(){
		return orders;
	}

	/**
	 * �����ı�����
	 * @return
	 */

	public String getText(){
		return text;
	}
}
