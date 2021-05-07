package com.callor.menu;

public class StringEx_01 {

	public static void main(String[] arg) {
		String menu = "1";
		if(menu.equals("1")) {
			System.out.println("menu is " + 1);
		} else if(menu.equals("2")) {
			System.out.println("menu is " + 2);
		} {
			System.out.println("menu is not " + 1);
		}
		
		/*
		 * 만약 변수에 저장된 값이 ㅜnull이면 미리 if 명령들을 이용 하여검사하거나
		 * 
		 * java 1.5 이전에는 switch 문으로 문자열을 조건처리 할 수 없었다
		 */
		if(menu != null) {
			switch (menu) {
			case "1":
				System.out.println("저장된 값은 3");

			case "2": {
				System.out.println();
			}
			case "3": {
				System.out.println();
				break;
			}
			}
		}
	}
}
