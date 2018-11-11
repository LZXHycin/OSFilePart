package myFile;

import java.io.Serializable;
import java.util.ArrayList;

import disk.MyDisk;

public class MyFile implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	//------------------------------------������-----------------------------------
	/**
	 * �ļ��Ƿ��ɾ��
	 */
	protected boolean deletable = true;
	/**
	 * �ļ���
	 */
	protected String name = null;
	/**
	 * �ļ���չ����'e'��ִ���ļ���'t'��ͨ�ı��ļ���' 'Ŀ¼
	 */
	protected char extensionName = ' ';
	/**
	 * �ļ�����:'r'ֻ��, 's'ϵͳ�ļ�, 'o'��ͨ�ļ�, 'f'Ŀ¼
	 */
	protected char attribute;
	/**
	 * �ļ�ռ�ô��̵ĳ�ʼ���̿��
	 */
	protected int originNum;
	/**
	 * �ļ���С
	 */
	protected int size = 0;
	/**
	 * �ļ���Ŀ¼
	 */
	protected MyFile parent = null;

	//---------------------------------���췽��---------------------------------
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

	//----------------------------------������ķ���---------------------------
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
	public boolean delete(){
		if (deletable) {
			Folder parent = (Folder) this.parent;
			parent.setSize(parent.getSize() - this.size);
			MyDisk.getDisk().getFat().printFat();
//		System.out.println("�״��̺�" + this.originNum);
//		System.out.println("ɾ��" + this.name);
//		Disk.fat.release(this.originNum);
			MyDisk.getDisk().getFat().release(this.originNum);
//		Disk.fat.printFat();
			parent.removeFile(this);
			return true;
		}else {
			return false;
		}
	}

	/**
	 * ������
	 * @param name �ļ���
	 */
	public boolean rename(String name){
		if (name.length() > 3) {
			return false;
		}else {
			this.name = name;
			return true;
		}
	}

	//---------------------------------�ⲿ��ʹ�õķ���------------------------
	/**
	 * �Ƿ�Ϊ�ļ���
	 * @return
	 */
	public boolean isFolder(){
		return false;
	}

	//--------------------------------get����----------------------------------
	/**
	 * @return�ļ����״��̿��
	 */
	public int getOriginNum(){
		return originNum;
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
	 * ��ȡ�ļ���С
	 * @return �ļ���С
	 */
	public int getSize(){
		return size;
	}

	//-----------------------------------set����-----------------------------------
	/**
	 * ����ռ�õ����̿飬�������ļ�ճ��ʱʹ��
	 * @param num ���̿��
	 */
	protected void setOriginNum(int num){
		this.originNum = num;
	}

	/**
	 * ���ø�Ŀ¼
	 * @param parent ��Ŀ¼
	 */
	public void setParent(MyFile parent){
		this.parent = parent;
	}

	/**
	 * �����ļ��Ĵ�С�������ڸ����ļ�ʱʹ��
	 * @param size �ļ���С
	 */
	protected void setSize(int size){
		this.size = size;
	}

	public boolean isFile() {
		return true;
	}

	public ArrayList<MyFile> getChildrenFiles() {
		return null;
	}
}
