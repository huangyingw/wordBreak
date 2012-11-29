import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class WordBreaker {
	public static void main(String[] args) {
		String test = "iloveyouhelloworld";
		Set<String> dict = new HashSet<String>();
		dict.add("i");
		dict.add("love");
		dict.add("you");
		dict.add("hello");
		dict.add("world");
		WordBreaker wb = new WordBreaker();
		System.out.println(wb.SegmentString(test, dict));
		System.out.println(wb.SegmentString1(test, dict));
		System.out.println(wb.SegmentString11(test, dict));
	}

	String SegmentString(String input, Set<String> dict) {
		int len = input.length();
		for (int i = 1; i < len; i++) {
			String prefix = input.substring(0, i);
			if (dict.contains(prefix)) {
				String suffix = input.substring(i, len);
				if (dict.contains(suffix)) {
					return prefix + " " + suffix;
				}
			}
		}
		return null;
	}

	String SegmentString1(String input, Set<String> dict) {
		if (dict.contains(input))
			return input;
		int len = input.length();
		for (int i = 1; i < len; i++) {
			String prefix = input.substring(0, i);
			if (dict.contains(prefix)) {
				String segSuffix = SegmentString1(input.substring(i, len), dict);
				if (segSuffix != null) {
					return prefix + " " + segSuffix;
				}
			}
		}
		return null;
	}

	Map<String, String> memoized = new HashMap<String, String>();

	String SegmentString11(String input, Set<String> dict) {
		if (dict.contains(input)) {
			memoized.put(input, input);
			return input;
		}
		if (memoized.containsKey(input))
			return memoized.get(input);
		for (int i = 0; i < input.length(); i++) {
			String preFix = input.substring(0, i);
			if (dict.contains(preFix)) {
				String newInput = input.substring(i, input.length());
				String postFix = SegmentString11(newInput, dict);
				if (postFix != null) {
					memoized.put(input, preFix + " " + postFix);
					return preFix + " " + postFix;
				}
			}

		}
		memoized.put(input, null);
		return null;
	}
}
