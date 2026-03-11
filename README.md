# Machine Learning System for Categorising UCI Handwritten Digits using Support Vector Machines

This project implements a Machine learning classification system for recognising handwritten digits using Support Vector Machines (SVM) algorithm. 
The objective of the system is to automatically classify numeric digits based on pixel-based feature representation derived from images of handwritten digits.
</br>
The system is trained and evaluated using a dataset from the **UCI Machine Learning Repository**, specifically the **[Optical Recognition of Handwritten Digits Dataset](https://archive.ics.uci.edu/dataset/80/optical+recognition+of+handwritten+digits)**.
This dataset contains digit images that are transformed into numerical feature vectors representing pixel intensities. The trained model learns to distinguish between different digits by indentifying patterns in these features.
</br>
The project demostrates how classical machine learning methods such as SVM can achive strong performance on image classification tasks without requiring deep learning architecures.

---
## Problem Statement 
Handwritten digit recognition is a **multiclass classification problem**  where the goal is to assign each input sample to one of the ten possible classes:
```
0,1,2,3,4,5,6,7,8,9
```
</br>
The challenge lies in the variability of handwritten digits:
- Different writting styles
- Variation in stroke thickness
- Position and orientation differences

A machine learning classifier must learn to generalise these patterns and accurately classify unseen samples.
---
## Dataset Description
The datased used comes from the **UCI Machine Learning Repository**.

### Dataset Characteristics
| Property | Value |
|------|------|
| Number of samples | ~1797 |
| number of classes | 10 |
| Featires per sample | 64 |
| image size | 8x8 pixels |
</br>
Each digit image is converted into a vector of **64 features**, representing the grayscale intesiity of each pixel.
</br>
The values range between:
```
0 - 16
```
representing pixel brightness.
</br>
The digit images were originally **32x32 bitmaps**, but were reduced to **8x8 blocks** to simplify the feature space and reduce dimenstionality.
---
## System Architecure
The machine learning pipeline consist of several stages:
```
Dataset Acquisition
Data Preprocessing
Feature Representation
Model Training (SVM)
Model Evaluation
Prediction
```
---
## Data Processing Pipeline
### Data Loading
The dataset is imported and stored as:
```
x = features (pixel values)
y = labels (digits 0-9)
```
Each row corresponds to a single diigit sample.
---
### Data Preprocessing
Typical preprocessing steps include:
- Normalising pixel values
- Splitting dataset into:
```
Training set
Testing set
```
Common split: 
```
70% training
30% testing
```
---
### Feature Representation
Each digit image is represented as:
```
8 x 8 pixel grid
```
Flattened into a vector:
```
64-dimensional feature vector
```
Example:
```
[0,0,5,16,2,1,0,0,...]
```
---
## Evaluation Metrics
The models performance is evaluated using classification metrics.
</br>
#Accuracy 
Measures the proportion of the correct predictions.
```
Accuracy = Correct predicitons / Total predictions
```
---
#Precision
```
Precision = TP / (TP + FP)
```
Measures how many predicted positives are correct.
---
# Recall
```
Recall = TP / (TP + FN)
```
Measires how many actual positives were corectly detected.
---
# F1 Score
Harmonic mean of precision and recall.
```
F1 = 2 * (precision * recall) / (precision + recall)
```
# Confusion Matrix
Shows how many samples were classified correctly and incorectly.
</br>
Example:
| Actual | Predicted | 
|------|------|
| 3 | 3 |
| 5 | 8 |
</br>
This helps identify which digits are frequently confused.
---
## Experimental Results
Typical performance of SVM on this dataset is: 
| Model | Accuracy |
|-----|-----|
| SVM | ~97-99% |
</br>
Research shows that SVM achuives extremely high accuracy on handwritten digit datasets due to ist strong generalisation ability.
