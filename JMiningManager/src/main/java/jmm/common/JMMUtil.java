package jmm.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jmm.model.Author;
import jmm.model.ChangedFile;
import jmm.model.FileChangeType;

public abstract class JMMUtil {

	public static List<ChangedFile> filterFilesByChangeType(List<ChangedFile> files, FileChangeType[] types) {
		return files;
	}

	public static List<Author> groupByAuthor(List<Author> authors) {
		Map<String, Author> number_of_changes = new HashMap<String, Author>();

		for (Author a : authors) {
			String key = a.getName() + a.getEmail();
			Author value = number_of_changes.get(key);

			if (value == null) {
				number_of_changes.put(key, a);
				a.setChanges(0);
				value = a;
			}

			value.setChanges(1 + value.getChanges());
		}

		List<Author> grouped = new ArrayList<Author>(number_of_changes.values());

		Collections.sort(grouped, new Comparator<Author>() {
			public int compare(Author o1, Author o2) {
				return o2.getChanges() - o1.getChanges();
			}
		});

		return grouped;
	}

}
