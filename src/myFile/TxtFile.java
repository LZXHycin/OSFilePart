package myFile;

import java.io.Serializable;
import disk.MyDisk;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TxtFile extends MyFile implements Serializable{

	//-----------------------------������----------------------
	/**
	 * �ı�����
	 */
	private String text;

	//-----------------------------���췽��------------------------
	/**
	 * ����ʱ���õĹ��췽��
	 * @param name�ļ���
	 */
	public TxtFile(String name, String text) {
		super(name);
		this.text = text;
		//��չ��Ϊt
		this.extensionName = 't';
		this.attribute = 'o';

	}

	/**
	 * �½������õĹ��췽��
	 * @param parent
	 */
	public TxtFile(MyFile parent){
		super(parent);
		this.name = "�½��ļ�";
		this.extensionName = 't';
		this.attribute = 'o';
	}

	//--------------------------�����෽��-----------------------
	/**
	 *�ļ�����
	 *����TxtFile����δָ����Ŀ¼
	 */
	@Override
	public MyFile copy() {
		//�½��ļ�
		TxtFile file = new TxtFile(this.name, this.text);
		//�����ı�����
		file.setSize(this.size);
		return file;
	}

	//-----------------------------set����-------------------------
	/**
	 * �޸��ı�����
	 * @param text
	 */
	public void setText(String text){
		this.text = text;
		//ԭ�����ļ���С
		int oldSize = this.size;
		//�ļ���С=���ݴ�С+���Դ�С
		this.size = text.length() + 8;
		//�ļ���С�ĸı�
		int changeSize  = this.size - oldSize;
		//ѭ���ı丸Ŀ¼�Ĵ�С
		MyFile parent = this.parent;
		while (parent != null) {
			parent.setSize(parent.getSize() + changeSize);
			parent = parent.getParent();
		}
		//���¸��ļ��ķ���FAT
		MyDisk.getDisk().getFat().changeByTextSize(this.size, this.originNum);
	}

	//--------------------------------get����----------------------------
	public String getText(){
		return text;
	}
}
