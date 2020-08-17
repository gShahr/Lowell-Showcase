/*
ID: gshahro1
LANG: C++
TASK: transform
*/
#include <bits/stdc++.h>
using namespace std;
#define N 10

bool same(char matrix1[N][N], char matrix2[N][N]) {
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if (matrix1[i][j] != matrix2[i][j])  {return false;}
    }
  }
  return true;
}

void rotate_90(char matrix[N][N], int n) {
  char copy[N][N];
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      copy[i][j] = matrix[n-j-1][i];
    }
  }
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      matrix[i][j] = copy[i][j];
    }
  }
} 

void horizontal(char matrix[N][N], int n) {
  char copy[N][N];
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      copy[i][j] = matrix[i][n-j-1];
    }
  }
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      matrix[i][j] = copy[i][j];
    }
  }
}

int main() {
  ifstream input("transform.in"); ofstream output("transform.out");
  int n; input >> n;
  char original[N][N], transformed[N][N];
  char c;
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if (i >= n || j >= n) {original[i][j] = ' ';}
      else {input >> c; original[i][j] = c;}
    }
  }
  for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
      if (i >= n || j >= n) {transformed[i][j] = ' ';}
      else {input >> c; transformed[i][j] = c;}
    }
  }
  rotate_90(original, n);
  if (same(original, transformed)) {
    output << 1 << endl;
    return 0;
  }
  rotate_90(original, n);
  if (same(original, transformed)) {
    output << 2 << endl;
    return 0;
  }
  rotate_90(original, n);
  if (same(original, transformed)) {
    output << 3 << endl;
    return 0;
  }
  rotate_90(original, n);
  horizontal(original, n);
  if (same(original, transformed)) {
    output << 4 << endl;
    return 0;
  }
  for (int i = 0; i < 3; i++) {
    rotate_90(original, n);
    if (same(original, transformed)) {
      output << 5 << endl;
      return 0;
    }
  }
  rotate_90(original, n);
  horizontal(original, n);
  if (same(original, transformed)) {
    output << 6 << endl;
    return 0;
  }
  output << 7 << endl;
  input.close(); output.close();
  return 0;
}
