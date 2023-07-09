class RegressorWrapper :
    def __init__(self, model, x, y, y_predicted):
        self.model = model
        self.x = x
        self.y = y
        self.y_predicted = y_predicted
    def get_residuals(self):
        return self.y - self.y_predicted
    def get_linear_equation(self):
        return "y =" + str(self.model.coef_[0][0]) + "x + " + str(self.model.intercept_[0])