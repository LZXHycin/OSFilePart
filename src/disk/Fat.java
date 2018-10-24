package disk;

import java.io.Serializable;

import myFile.MyFile;

public class Fat implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	//�ļ������
	public int[] fat;

	public Fat(){
		//��ʼ��FAT��:0,1,2,3ΪFATռ�ã�4Ϊ��Ŀ¼ռ��
		fat = new int[256];
		for (int i = 0; i < 5; i++) {
			fat[i] = -1;
		}
		for (int i = 5; i < fat.length; i++) {
			fat[i] = 0;
		}
	}

	/**
	 * Ѱ�ҵ�һ�����д��̿�
	 * @return 5-255�Ĵ��̿��
	 */
	private int seekFreeBlockNum(){
		int index;
		for (index = 0; index < fat.length; index++) {
			if (fat[index] == 0) {
				break;
			}
		}
		return index;
	}

	/**
	 * FAT���ά������</br>
	 * �ļ����ݸ��Ķ��޸�FAT��</br>
	 * ʹ��ʱ�䣺�޸��ı��ļ�(TxtTile,ExeFile)����ʱ</br>
	 * ʹ�õط���TxtTile,ExeFile��setText����
	 * @param size �ļ���С
	 * @param originNum �״��̿��
	 */
	public void changeByTextSize(int size, int originNum){
		//���ļ��Ѿ�ʹ�õĴ��̿�����
		int usedNum = 1;
		int index = originNum;
		while (fat[index] != -1) {
			usedNum ++;
			index = fat[index];
		}
		//��¼ԭ�������һ�����̿�
		int last = index;
		//�����ı����ݺ�����Ҫ�Ĵ��̿�����
		int nowNum = (size + 63) / 64;

		if (usedNum > nowNum) {  //ԭ����ռ�õĴ��̿�϶࣬��ѡȡǰ����
			index = originNum;
			for (int i = 1; i < nowNum; ++i){
				index = fat[index];
			}
			int next = fat[index];
			fat[index] = -1;
			while (fat[next] != -1) {
				index = fat[next];
				fat[next] = 0;
				next = index;
			}
			fat[next] = 0;
		}else if (usedNum == nowNum) { //��ռ�ô��̿������䣬�������

		}else {  //����Ҫ����Ĵ��̿飬Ѱ�ҿյ��̿�
			int need = nowNum - usedNum;
			for (int i = 0; i < need; ++i){
				fat[last] = seekFreeBlockNum();
				last = fat[last];
				fat[last] = -1;
			}
		}

	}

	/**
	 * FAT���ά������</br>
	 * �½��ļ�ʱ����FAT����</br>
	 * ʹ��ʱ�䣺��δʹ��</br>
	 * ʹ�õط�����δʹ��</br>
	 * @return ���½��ļ����״��̿��
	 */
	public int changeByFileCreate(){
		int index = seekFreeBlockNum();
		fat[index] = -1;
		return index;
	}

	/**
	 * FAT���ά������</br>
	 * ���ݸ����ļ��Ĵ�С��������Ӧ��FAT����������״��̿��</br>
	 * ʹ��ʱ�䣺�ļ�����ʱ</br>
	 * ʹ�õط���Folder��pasteճ������</br>
	 * @param size �����ļ��Ĵ�С
	 * @return�״��̿��
	 */
	public int changeByFileCopy(MyFile file){
		if (file.isFolder()) {
			int freeOne = seekFreeBlockNum();
			fat[freeOne] = -1;
			return freeOne;
		}else {
			int size = file.getSize();
			int nowNum = (size + 63) / 64;
			int[] request = new int[nowNum];
			int allow = 0;
			for (int i = 5; i < fat.length && allow < nowNum; ++i){
				if (fat[i] == 0) {
					request[allow++] = i;
				}
			}
			for (int i = 0; i < request.length-1; i++) {
				fat[request[i]] = request[i+1];
			}
			fat[request[nowNum-1]] = -1;
			return request[0];
		}
	}

	/**
	 * ��ɾ���ļ�ʱ���ͷŴ��̿ռ䣬�ı�FAT��
	 * @param originNum �״��̿��
	 */
	public void release(int originNum){
		int index = originNum;
		while (fat[index] != -1) {
			int i = fat[index];
			fat[index] = 0;
			index = i;
		}
		fat[index] = 0;
	}

	/**
	 * ��ȡFAT��
	 * @return
	 */
	public int[] getFat(){
		return fat;
	}

	/**
	 * ���FAT��ǰ30��
	 */
	public void printFat(){
		for (int i = 0; i < 30; i++) {
			System.out.print(fat[i] + " ");
		}
		System.out.println();
	}
}
