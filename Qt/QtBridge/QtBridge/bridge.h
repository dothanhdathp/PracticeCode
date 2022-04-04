#ifndef BRIDGE_H
#define BRIDGE_H
#include <QString>
#include <QObject>
#include <QDebug>

/*
    Firstly, must inherite QObject to connect C++ -> Qt
*/
class Bridge : public QObject
{
    Q_OBJECT
    // property
public:
private:
    QString mCurrentText;

    // function
public:
    Bridge(QObject *parent);
    ~Bridge();
    Q_INVOKABLE QString sayHello() {return QString("Hello world!");}
private:
    QString testGetText() {return mCurrentText;}
    void    setText(QString input) {mCurrentText = input;}
    void    getAns() {
        qDebug("Answered!!!");
    };
    void    readData();
    void    convertData();
};

#endif // BRIDGE_H
