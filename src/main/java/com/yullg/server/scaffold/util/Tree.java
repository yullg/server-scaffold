package com.yullg.server.scaffold.util;

import java.util.ArrayList;
import java.util.List;

public abstract class Tree {

	public static <I, O> List<O> wrap(Iterable<I> originals, Wrap<I, O> hook) {
		List<O> result = new ArrayList<>();
		for (I original : originals) {
			if (hook.isRoot(original, originals)) {
				O o = wrapInner(original, originals, hook);
				if (o != null) {
					result.add(o);
				}
			}
		}
		return result;
	}

	public static <I, O> List<O> unwrap(I original, Unwrap<I, O> hook) {
		List<O> result = new ArrayList<>();
		O o = hook.cast(original, null);
		if (o != null) {
			result.add(o);
		}
		unwrapInner(original, result, hook);
		return result;
	}

	private static <I, O> O wrapInner(I branch, Iterable<I> originals, Wrap<I, O> hook) {
		List<O> children = new ArrayList<>();
		for (I original : originals) {
			if (hook.isChild(branch, original)) {
				O o = wrapInner(original, originals, hook);
				if (o != null) {
					children.add(o);
				}
			}
		}
		return hook.merge(branch, children);
	}

	private static <I, O> void unwrapInner(I original, List<O> result, Unwrap<I, O> hook) {
		List<I> children = hook.children(original);
		if (children != null) {
			for (I child : children) {
				O o = hook.cast(child, original);
				if (o != null) {
					result.add(o);
				}
				unwrapInner(child, result, hook);
			}
		}
	}

	public static interface Wrap<I, O> {
		/**
		 * 判断original是不是根节点？
		 */
		boolean isRoot(I original, Iterable<I> originals);

		/**
		 * 判断original是不是branch的子节点？
		 */
		boolean isChild(I branch, I original);

		/**
		 * 合并branch和它的所有子节点children，方法返回NULL则排除当前节点？
		 */
		O merge(I branch, List<O> children);
	}

	public static interface Unwrap<I, O> {
		/**
		 * 由当前节点original和它的上级节点parent产生1个输出对象，如果当前节点是根节点，parent参数为NULL，方法返回NULL则排除当前节点。
		 */
		O cast(I original, I parent);

		/**
		 * 查询original下一级的所有节点。
		 */
		List<I> children(I original);
	}

}