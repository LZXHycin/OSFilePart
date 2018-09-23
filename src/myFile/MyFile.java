package myFile;

import java.io.Serializable;

import disk.MyDisk;
import javafx.scene.image.ImageView;

public class MyFile implements Serializable{

	//�ļ���,3B
	protected String name = null;
	/**
	 * �ļ���չ����'e'��ִ���ļ���'t'��ͨ�ı��ļ���' 'Ŀ¼
	 */
	protected char extensionName = ' ';
	/**
	 * �ļ�����:'r'ֻ��, 's'ϵͳ�ļ�, 'o'��ͨ�ļ�, 'f'Ŀ¼
	 */
	protected char attribute;
	//��ʼ�̺�,1B
	protected int originNum;
	//�ļ���С(��λΪB),2B
	protected int size = 0;
	//��Ŀ¼
	protected MyFile parent = null;

	/**
	 * �޲ι��췽����ֻ���ڸ�Ŀ¼����
	 */
	public MyFile(){
		this.size = 8;
	}

	/**
	 * ����ʱ���õĹ��췽��
	 * @param name�ļ���
	 */
	public MyFile(String name){
		this();
		this.name = name;
	}

	/**
	 * �½��ļ�ʱ���õĹ��췽��
	 * @param parent��Ŀ¼
	 */
	public MyFile(MyFile parent){
		this();
		this.parent = parent;
	}



	/**
	 * ����
	 * @return Myfile����
	 */
	public MyFile copy() {
		return null;
	}

	/**
	 * ɾ��
	 */
	public void delete(){
		Folder parent = (Folder) this.parent;
		parent.setSize(parent.getSize() - this.size);
		MyDisk.disk.getFat().printFat();
//		System.out.println("�״��̺�" + this.originNum);
//		System.out.println("ɾ��" + this.name);
//		Disk.fat.release(this.originNum);
		MyDisk.disk.getFat().release(this.originNum);
//		Disk.fat.printFat();
		parent.removeFile(this);
	}

	/**
	 * ������
	 * @param name �ļ���
	 */
	public void rename(String name){
		this.name = name;
	}




	/**
	 * ��ȡ�ļ����״��̿��
	 * @return
	 */
	public int getOriginNum(){
		return originNum;
	}

	/**
	 * ����ռ�õ����̿飬�������ļ�ճ��ʱʹ��
	 * @param num ���̿��
	 */
	protected void setOriginNum(int num){
		this.originNum = num;
	}

	/**
	 * ��ȡ�ļ���
	 * @return �ļ���
	 */
	public String getName(){
		return name;
	}

	/**
	 * ��ȡ��Ŀ¼
	 * @return Myfile����
	 */
	public MyFile getParent(){
		return parent;
	}

	/**
	 * ���ø�Ŀ¼
	 * @param parent ��Ŀ¼
	 */
	public void setParent(MyFile parent){
		this.parent = parent;
	}

	/**
	 * ��ȡ�ļ���С
	 * @return �ļ���С
	 */
	public int getSize(){
		return size;
	}

	/**
	 * �����ļ��Ĵ�С�������ڸ����ļ�ʱʹ��
	 * @param size �ļ���С
	 */
	protected void setSize(int size){
		this.size = size;
	}



	/**
	 * �Ƿ�Ϊ�ļ���
	 * @return
	 */
	public boolean isFolder(){
		return false;
	}

}
