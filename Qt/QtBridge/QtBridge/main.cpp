// C++ library
#include "bridge.h"
// Qt library
#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include <QQmlContext>

int main(int argc, char *argv[])
{
    // don't know this design:D
    QCoreApplication::setAttribute(Qt::AA_EnableHighDpiScaling);
    QGuiApplication app(argc, argv);

    Bridge UiBridge(&app);
    QQmlApplicationEngine engine;
    const QUrl url(QStringLiteral("qrc:/main.qml"));
    QObject::connect(&engine, &QQmlApplicationEngine::objectCreated, &app, [url](QObject *obj, const QUrl &objUrl) {
        if (!obj && url == objUrl) QCoreApplication::exit(-1);
    }, Qt::QueuedConnection);


    engine.load(url);

    // Make and Bridge Object

    QQmlContext *rootContext = engine.rootContext();
    rootContext->setContextProperty("UiBridge", &UiBridge);
    return app.exec();
}
