package disk;

import java.io.Serializable;

import myFile.MyFile;

public class Fat implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	//文件分配表
	public int[] fat;

	public Fat(){
		//初始化FAT表:0,1,2,3为FAT占用，4为根目录占用
		fat = new int[256];
		for (int i = 0; i < 5; i++) {
			fat[i] = -1;
		}
		for (int i = 5; i < fat.length; i++) {
			fat[i] = 0;
		}
	}

	/**
	 * 寻找第一个空闲磁盘块
	 * @return 5-255的磁盘块号
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
	 * FAT表的维护更改</br>
	 * 文件内容更改而修改FAT表</br>
	 * 使用时间：修改文本文件(TxtTile,ExeFile)内容时</br>
	 * 使用地方：TxtTile,ExeFile的setText方法
	 * @param size 文件大小
	 * @param originNum 首磁盘块号
	 */
	public void changeByTextSize(int size, int originNum){
		//该文件已经使用的磁盘块数量
		int usedNum = 1;
		int index = originNum;
		while (fat[index] != -1) {
			usedNum ++;
			index = fat[index];
		}
		//记录原本的最后一个磁盘块
		int last = index;
		//更改文本内容后所需要的磁盘块数量
		int nowNum = (size + 63) / 64;

		if (usedNum > nowNum) {  //原本所占用的磁盘块较多，则选取前部分
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
		}else if (usedNum == nowNum) { //所占用磁盘块数不变，无须操作

		}else {  //现需要更多的磁盘块，寻找空的盘块
			int need = nowNum - usedNum;
			for (int i = 0; i < need; ++i){
				fat[last] = seekFreeBlockNum();
				last = fat[last];
				fat[last] = -1;
			}
		}

	}

	/**
	 * FAT表的维护更改</br>
	 * 新建文件时分配FAT表项</br>
	 * 使用时间：暂未使用</br>
	 * 使用地方：暂未使用</br>
	 * @return 该新建文件的首磁盘块号
	 */
	public int changeByFileCreate(){
		int index = seekFreeBlockNum();
		fat[index] = -1;
		return index;
	}

	/**
	 * FAT表的维护更改</br>
	 * 根据复制文件的大小，分配相应的FAT表项，并返回首次盘块号</br>
	 * 使用时间：文件复制时</br>
	 * 使用地方：Folder的paste粘贴方法</br>
	 * @param size 复制文件的大小
	 * @return首次盘块号
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
	 * 当删除文件时，释放磁盘空间，改变FAT表
	 * @param originNum 首磁盘块号
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
	 * 获取FAT表
	 * @return
	 */
	public int[] getFat(){
		return fat;
	}

	/**
	 * 输出FAT表前30项
	 */
	public void printFat(){
		for (int i = 0; i < 30; i++) {
			System.out.print(fat[i] + " ");
		}
		System.out.println();
	}
}
