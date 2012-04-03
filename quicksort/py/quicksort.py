#! /usr/bin/python

import sys
import math
from random import shuffle

def swap(a, fi, ti):
	to,fr = a[ti],a[fi]
	a[ti],a[fi] = fr,to
	
def pivot1(a, s, l):
	return s
	
def pivot2(a, s, l):
	return l - 1
	
def pivot3(a, s, l):
	fi = s
	li = l - 1
	mi = fi + int(math.floor((li - fi) / 2))

	tmp = sorted([ [a[fi], fi], [a[li], li], [a[mi], mi] ], key=lambda t: t[0])
	
	return tmp[1][1]
	
def quicksort(pf, a, s, l):
	if (l - s) <= 1:
		return 0
		
	c = l - s - 1
		
	p = pf(a, s, l)
	swap(a, p, s)
	
	i = s + 1
	
	for j in range(s,l):
		if a[j] < a[s]:
			swap(a, j, i)
			i = i + 1
	
	pe = i - 1
	swap(a, pe, s)
	
	c = c + quicksort(pf, a, s, pe)
	c = c + quicksort(pf, a, i, l)
	
	return c		
	
#lines = [int(line.strip()) for line in open('/Users/eric/Desktop/QuickSort.txt')]
#quicksort(pivot3, lines, 0, len(lines))
	