package myFile;

import java.io.Serializable;

import disk.MyDisk;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ExeFile extends MyFile implements Serializable{

	//�ı�����
	private String text;
	private String[] orders;

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
		this.name = "�½��ļ�";
		this.extensionName = 'e';
		this.attribute = 'o';
	}


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
		MyDisk.disk.getFat().changeByTextSize(this.size, this.originNum);
	}

	/**
	 *������������
	 * @return
	 */
	public String[] getOrders(){
		return orders;
	}
}
