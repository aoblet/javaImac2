package fr.upem.test;

public class PrintArgs {
	public static void main(String[]args){
		if(args.length > 0)
			System.out.println("1er arg: " + args[0]);
		
		for(String arg: args)
			System.out.println(arg);
	}
}
